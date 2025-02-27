package com.inno.hackaton2025.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import com.inno.hackaton2025.model.models._
import com.inno.hackaton2025.service.Neo4jService

import scala.concurrent.ExecutionContext

class ApiRoutes(neo4jService: Neo4jService)(implicit ec: ExecutionContext) {

  val route: Route =
    path("import") {
      post {
        entity(as[String]) { json =>
          JsonParser.parseJson(json) match {
            case Right(importData) =>
              neo4jService.importData(importData)
              complete(StatusCodes.OK, "Data imported successfully into Neo4j!")
            case Left(error) =>
              complete(StatusCodes.BadRequest, s"Invalid JSON: ${error.getMessage}")
          }
        }
      }
    }
}

