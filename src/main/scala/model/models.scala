package com.inno.hackaton2025.model

import io.circe.generic.auto._
import io.circe.parser._

object models {

  case class FollowupQuestion(name: String, `type`: String, difficulty: String, weight: Int)

  case class Question(name: String, `type`: String, difficulty: String, weight: Int, followupQuestions: List[FollowupQuestion])

  case class Theme(name: String, questions: List[Question])

  case class Topic(name: String, themes: List[Theme])

  case class Domain(name: String, topics: List[Topic])

  case class ImportRequest(domains: List[Domain])

  object JsonParser {
    def parseJson(json: String): Either[io.circe.Error, ImportRequest] = decode[ImportRequest](json)
  }

}
