package com.inno.hackaton2025.api

import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.headers.ContentDispositionTypes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.headers
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.StatusCodes
import io.circe.syntax._
import io.circe.generic.auto._
import io.swagger.v3.oas.annotations._
import io.swagger.v3.oas.annotations.media._
import io.swagger.v3.oas.annotations.parameters._
import io.swagger.v3.oas.annotations.responses._
import io.swagger.v3.oas.annotations.tags._
import jakarta.ws.rs._
import scala.concurrent.ExecutionContext

import com.inno.hackaton2025.model.models._
import com.inno.hackaton2025.service.Neo4jService

@Tag(name = "Import API", description = "API for importing data into Neo4j")
@Path("/api")
class ApiRoutes(neo4jService: Neo4jService)(implicit ec: ExecutionContext) {

  @POST
  @Path("/import")
  @Operation(
    summary = "Import JSON data into Neo4j",
    description = "Takes a JSON payload and imports data into the Neo4j database.",
    requestBody = new RequestBody(
      content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[String])))
    ),
    responses = Array(
      new ApiResponse(responseCode = "200", description = "Data imported successfully"),
      new ApiResponse(responseCode = "400", description = "Invalid JSON format")
    )
  )
  def importDataRoute: Route = {
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
  @GET
  @Path("/export")
  @Operation(
    summary = "Export data from Neo4j as JSON",
    description = "Exports data from the Neo4j database and returns it as a JSON file.",
    responses = Array(
      new ApiResponse(
        responseCode = "200",
        description = "JSON file containing exported data",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[String])))
      ),
      new ApiResponse(responseCode = "500", description = "Internal server error")
    )
  )
  def exportDataRoute: Route = {
    path("export") {
      get {
        try {
          val data = neo4jService.exportData()
          val jsonString = data.asJson.spaces2

          val entity = HttpEntity(ContentTypes.`application/json`, jsonString)

          respondWithHeader(headers.`Content-Disposition`(ContentDispositionTypes.attachment, Map("filename" -> "exported_data.json"))) {
            complete(entity)
          }
        } catch {
          case ex: Exception =>
            complete(StatusCodes.InternalServerError -> s"An error occurred: ${ex.getMessage}")
        }
      }
    }
  }

  val route: Route = pathPrefix("api") {
    importDataRoute ~ exportDataRoute
  }
}

