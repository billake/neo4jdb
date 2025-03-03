package com.inno.hackaton2025.service

import org.neo4j.driver.{AuthTokens, Driver, GraphDatabase, Session}
import com.inno.hackaton2025.model.models._
import com.inno.hackaton2025.util.CypherUtils.escapeCypherString

import java.util.UUID
import scala.jdk.CollectionConverters.CollectionHasAsScala

class Neo4jService(uri: String, user: String, password: String) {

  private val driver: Driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password))

  def close(): Unit = driver.close()

  def importData(data: ImportExportRequest): Unit = {
    val session: Session = driver.session()

    try {
      data.domains.foreach { domain =>
        val domainId = domain.id.getOrElse(UUID.randomUUID())
        val domainQuery =
          s"""
             |MERGE (d:Domain {id: '${domainId.toString}', title: '${escapeCypherString(domain.title)}'})
             |""".stripMargin
        session.run(domainQuery)

        domain.topics.foreach { topic =>
          val topicId = topic.id.getOrElse(UUID.randomUUID())
          val topicQuery =
            s"""
               |MERGE (d:Domain {id: '${domainId.toString}'})
               |MERGE (t:Topic {id: '${topicId.toString}', title: '${escapeCypherString(topic.title)}'})
               |MERGE (d)-[:HAS_TOPIC]->(t)
               |""".stripMargin
          session.run(topicQuery)

          topic.themes.foreach { theme =>
            val themeId = theme.id.getOrElse(UUID.randomUUID())
            val themeQuery =
              s"""
                 |MERGE (t:Topic {id: '${topicId.toString}'})
                 |MERGE (th:Theme {id: '${themeId.toString}', title: '${escapeCypherString(theme.title)}'})
                 |MERGE (t)-[:HAS_THEME]->(th)
                 |""".stripMargin
            session.run(themeQuery)

            theme.questions.foreach { question =>
              val questionId = question.id.getOrElse(UUID.randomUUID())
              val tagsCypher = question.tags.map(tag => s"'${escapeCypherString(tag)}'").mkString("[", ", ", "]")
              val questionQuery =
                s"""
                   |MERGE (th:Theme {id: '${themeId.toString}'})
                   |MERGE (q:Question {id: '${questionId.toString}', weight: ${question.weight}, tags: $tagsCypher, title: '${escapeCypherString(question.title)}'})
                   |MERGE (th)-[:HAS_QUESTION]->(q)
                   |""".stripMargin
              session.run(questionQuery)

              question.followUpQuestions.foreach { fq =>
                val fqId = fq.id.getOrElse(UUID.randomUUID())
                val fqTagsCypher = fq.tags.map(tag => s"'${escapeCypherString(tag)}'").mkString("[", ", ", "]")
                val fqQuery =
                  s"""
                     |MERGE (q:Question {id: '${questionId.toString}'})
                     |MERGE (fq:FollowUpQuestion {id: '${fqId.toString}', weight: ${fq.weight}, tags: $fqTagsCypher, title: '${escapeCypherString(fq.title)}'})
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

  def exportData(): ImportExportRequest = {
    val session: Session = driver.session()

    try {
      val domainQuery = "MATCH (d:Domain) RETURN d.id, d.title"
      val domainResult = session.run(domainQuery)

      val domains = domainResult.list().asScala.map { record =>
        val domainId = UUID.fromString(record.get("d.id").asString())
        val domainTitle = record.get("d.title").asString()

        val topicQuery =
          s"""
             |MATCH (d:Domain {id: '${domainId.toString}'})-[:HAS_TOPIC]->(t:Topic)
             |RETURN t.id, t.title
             |""".stripMargin
        val topicResult = session.run(topicQuery)

        val topics = topicResult.list().asScala.map { record =>
          val topicId = UUID.fromString(record.get("t.id").asString())
          val topicTitle = record.get("t.title").asString()

          val themeQuery =
            s"""
               |MATCH (t:Topic {id: '${topicId.toString}'})-[:HAS_THEME]->(th:Theme)
               |RETURN th.id, th.title
               |""".stripMargin
          val themeResult = session.run(themeQuery)

          val themes = themeResult.list().asScala.map { record =>
            val themeId = UUID.fromString(record.get("th.id").asString())
            val themeTitle = record.get("th.title").asString()

            val questionQuery =
              s"""
                 |MATCH (th:Theme {id: '${themeId.toString}'})-[:HAS_QUESTION]->(q:Question)
                 |RETURN q.id, q.title, q.weight, q.tags
                 |""".stripMargin
            val questionResult = session.run(questionQuery)

            val questions = questionResult.list().asScala.map { record =>
              val questionId = UUID.fromString(record.get("q.id").asString())
              val questionTitle = record.get("q.title").asString()
              val questionWeight = record.get("q.weight").asInt()
              val tags = record.get("q.tags").asList().asScala.map(_.toString).toList

              val followUpQuery =
                s"""
                   |MATCH (q:Question {id: '${questionId.toString}'})-[:HAS_FOLLOWUP]->(fq:FollowUpQuestion)
                   |RETURN fq.id, fq.title, fq.weight, fq.tags
                   |""".stripMargin
              val followUpResult = session.run(followUpQuery)

              val followUpQuestions = followUpResult.list().asScala.map { record =>
                val fqId = UUID.fromString(record.get("fq.id").asString())
                val fqTitle = record.get("fq.title").asString()
                val fqWeight = record.get("fq.weight").asInt()
                val fqTags = record.get("fq.tags").asList().asScala.map(_.toString).toList

                FollowUpQuestion(Some(fqId), fqWeight, fqTags, fqTitle)
              }.toList

              Question(Some(questionId), questionWeight, tags, questionTitle, followUpQuestions)
            }.toList

            Theme(Some(themeId), themeTitle, questions)
          }.toList

          Topic(Some(topicId), topicTitle, themes)
        }.toList

        Domain(Some(domainId), domainTitle, topics)
      }.toList

      ImportExportRequest(domains)
    } finally {
      session.close()
    }
  }

}
