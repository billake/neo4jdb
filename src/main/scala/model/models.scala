package com.inno.hackaton2025.model

import io.circe.generic.auto._
import io.circe.parser._

import java.util.UUID

object models {

  case class FollowUpQuestion(id: Option[UUID], weight: Int, tags: List[String], title: String)

  case class Question(id: Option[UUID], weight: Int, tags: List[String], title: String, followUpQuestions: List[FollowUpQuestion])

  case class Theme(id: Option[UUID], title: String, questions: List[Question])

  case class Topic(id: Option[UUID], title: String, themes: List[Theme])

  case class Domain(id: Option[UUID], title: String, topics: List[Topic])

  case class ImportExportRequest(domains: List[Domain])

  object JsonParser {
    def parseJson(json: String): Either[io.circe.Error, ImportExportRequest] = decode[ImportExportRequest](json)
  }

}
