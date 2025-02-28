package com.inno.hackaton2025.service

import org.neo4j.driver.{AuthTokens, Driver, GraphDatabase, Session}
import com.inno.hackaton2025.model.models._
import com.inno.hackaton2025.util.CypherUtils.escapeCypherString

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
}
