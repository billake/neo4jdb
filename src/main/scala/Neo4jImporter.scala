package com.inno.hackaton2025

import org.neo4j.driver.{Session, Transaction}
import scala.jdk.CollectionConverters._
import com.inno.hackaton2025.Neo4jConnector._

object Neo4jImporter {

  def importData(interview: Interview): Unit = {
    val session: Session = Neo4jConnector.getSession

    try {
      session.writeTransaction { tx =>
        interview.categories.foreach { category =>
          createDomain(tx, category)
          category.questions.foreach { question =>
            createQuestion(tx, category.id, question)
            question.sub_questions.foreach(_.foreach(sub => createFollowUp(tx, question.id, sub)))
          }
        }
      }
      println("Data successfully loaded into Neo4j!")

    } catch {
      case e: Exception => println(s"Data loading error: ${e.getMessage}")
    } finally {
      session.close()
    }
  }

  private def createDomain(tx: Transaction, category: Category): Unit = {
    val params: java.util.Map[String, Object] = Map(
      "id" -> category.id.asInstanceOf[Object],
      "title" -> category.title.asInstanceOf[Object]
    ).asJava

    tx.run(
      """MERGE (d:Domain {id: $id, title: $title})""",
      params
    )
  }

  private def createQuestion(tx: Transaction, domainId: String, question: Question): Unit = {
    val params: java.util.Map[String, Object] = Map(
      "domain_id" -> domainId.asInstanceOf[Object],
      "id" -> question.id.asInstanceOf[Object],
      "text" -> question.text.asInstanceOf[Object],
      "type" -> question.`type`.asInstanceOf[Object],
      "difficulty" -> question.difficulty.asInstanceOf[Object],
      "weight" -> Integer.valueOf(question.weight).asInstanceOf[Object]
    ).asJava

    tx.run(
      """MATCH (d:Domain {id: $domain_id})
         MERGE (q:Question {id: $id, text: $text, type: $type, difficulty: $difficulty, weight: $weight})
         MERGE (d)-[:HAS_QUESTION]->(q)
      """,
      params
    )
  }

  private def createFollowUp(tx: Transaction, parentId: String, sub: SubQuestion): Unit = {
    val params: java.util.Map[String, Object] = Map(
      "parent_id" -> parentId.asInstanceOf[Object],
      "id" -> sub.id.asInstanceOf[Object],
      "text" -> sub.text.asInstanceOf[Object],
      "type" -> sub.`type`.asInstanceOf[Object],
      "difficulty" -> sub.difficulty.asInstanceOf[Object],
      "weight" -> Integer.valueOf(sub.weight).asInstanceOf[Object]
    ).asJava

    tx.run(
      """MATCH (q:Question {id: $parent_id})
         MERGE (sq:Question {id: $id, text: $text, type: $type, difficulty: $difficulty, weight: $weight})
         MERGE (q)-[:HAS_FOLLOWUP]->(sq)
      """,
      params
    )
  }
}
