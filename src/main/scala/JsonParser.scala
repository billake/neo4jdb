package com.inno.hackaton2025

import io.circe.generic.auto._
import io.circe.parser._
import scala.io.Source

case class SubQuestion(id: String, text: String, `type`: String, difficulty: String, weight: Int)

case class Question(id: String, text: String, `type`: String, difficulty: String, weight: Int, sub_questions: Option[List[SubQuestion]])

case class Category(id: String, title: String, questions: List[Question])

case class Interview(id: String, title: String, categories: List[Category])

case class RootInterview(interview: Interview)

object JsonParser {
  def loadJson(filePath: String): Option[Interview] = {
    val source = Source.fromFile(filePath)
    val jsonStr = try source.mkString finally source.close()
    decode[RootInterview](jsonStr) match {
      case Right(root) => Some(root.interview)
      case Left(error) =>
        println(s"Json parsing error: $error")
        None
    }
  }
}
