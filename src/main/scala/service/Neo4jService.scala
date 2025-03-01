package com.inno.hackaton2025.service

import org.neo4j.driver.{AuthTokens, Driver, GraphDatabase, Session}
import com.inno.hackaton2025.model.models._
import com.inno.hackaton2025.util.CypherUtils.escapeCypherString

import scala.jdk.CollectionConverters.CollectionHasAsScala

class Neo4jService(uri: String, user: String, password: String) {

  private val driver: Driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password))

  def close(): Unit = driver.close()

  def importData(data: ImportRequest): Unit = {
    val session: Session = driver.session()

    try {
      data.domains.foreach { domain =>
        val domainQuery =
          s"""
             |MERGE (d:Domain {name: '${domain.name}'})
             |""".stripMargin
        session.run(domainQuery)

        domain.topics.foreach { topic =>
          val topicQuery =
            s"""
               |MERGE (d:Domain {name: '${domain.name}'})
               |MERGE (t:Topic {name: '${topic.name}'})
               |MERGE (d)-[:HAS_TOPIC]->(t)
               |""".stripMargin
          session.run(topicQuery)

          topic.themes.foreach { theme =>
            val themeQuery =
              s"""
                 |MERGE (t:Topic {name: '${topic.name}'})
                 |MERGE (th:Theme {name: '${theme.name}'})
                 |MERGE (t)-[:HAS_THEME]->(th)
                 |""".stripMargin
            session.run(themeQuery)

            theme.questions.foreach { question =>
              val questionQuery =
                s"""
                   |MERGE (th:Theme {name: '${theme.name}'})
                   |MERGE (q:Question {name: '${escapeCypherString(question.name)}', type: '${question.`type`}', difficulty: '${question.difficulty}', weight: ${question.weight}})
                   |MERGE (th)-[:HAS_QUESTION]->(q)
                   |""".stripMargin
              session.run(questionQuery)

              question.followupQuestions.foreach { fq =>
                val fqQuery =
                  s"""
                     |MERGE (q:Question {name: '${escapeCypherString(question.name)}'})
                     |MERGE (fq:FollowupQuestion {name: '${escapeCypherString(fq.name)}', type: '${fq.`type`}', difficulty: '${fq.difficulty}', weight: ${fq.weight}})
                     |MERGE (q)-[:HAS_FOLLOWUP]->(fq)
                     |""".stripMargin
                session.run(fqQuery)
              }
            }
          }
        }
      }
    } finally {
      session.close()
    }
  }

  def exportData(): ImportRequest = {
    val session: Session = driver.session()

    try {
      val domainQuery = "MATCH (d:Domain) RETURN d.name"
      val domainResult = session.run(domainQuery)

      val domains = domainResult.list().asScala.map { record =>
        val domainName = record.get("d.name").asString()

        val topicQuery =
          s"""
             |MATCH (d:Domain {name: '$domainName'})-[:HAS_TOPIC]->(t:Topic)
             |RETURN t.name
             |""".stripMargin
        val topicResult = session.run(topicQuery)

        val topics = topicResult.list().asScala.map { record =>
          val topicName = record.get("t.name").asString()

          val themeQuery =
            s"""
               |MATCH (t:Topic {name: '$topicName'})-[:HAS_THEME]->(th:Theme)
               |RETURN th.name
               |""".stripMargin
          val themeResult = session.run(themeQuery)

          val themes = themeResult.list().asScala.map { record =>
            val themeName = record.get("th.name").asString()

            val questionQuery =
              s"""
                 |MATCH (th:Theme {name: '$themeName'})-[:HAS_QUESTION]->(q:Question)
                 |RETURN q.name, q.type, q.difficulty, q.weight
                 |""".stripMargin
            val questionResult = session.run(questionQuery)

            val questions = questionResult.list().asScala.map { record =>
              val questionName = record.get("q.name").asString()
              val questionType = record.get("q.type").asString()
              val difficulty = record.get("q.difficulty").asString()
              val weight = record.get("q.weight").asInt()

              val followupQuery =
                s"""
                   |MATCH (q:Question {name: '${escapeCypherString(questionName)}'})-[:HAS_FOLLOWUP]->(fq:FollowupQuestion)
                   |RETURN fq.name, fq.type, fq.difficulty, fq.weight
                   |""".stripMargin
              val followupResult = session.run(followupQuery)

              val followupQuestions = followupResult.list().asScala.map { record =>
                FollowupQuestion(
                  record.get("fq.name").asString(),
                  record.get("fq.type").asString(),
                  record.get("fq.difficulty").asString(),
                  record.get("fq.weight").asInt()
                )
              }.toList

              Question(questionName, questionType, difficulty, weight, followupQuestions)
            }.toList

            Theme(themeName, questions)
          }.toList

          Topic(topicName, themes)
        }.toList

        Domain(domainName, topics)
      }.toList

      ImportRequest(domains)
    } finally {
      session.close()
    }
  }
}
