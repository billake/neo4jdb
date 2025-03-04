package com.inno.hackaton2025

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.Materializer
import com.inno.hackaton2025.api._
import com.inno.hackaton2025.service.Neo4jService
import com.inno.hackaton2025.swagger.SwaggerDocService
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContext
import scala.util._

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("Neo4jImportSystem")
  implicit val materializer: Materializer = Materializer(system)
  implicit val executionContext: ExecutionContext = system.dispatcher

  val neo4jService = new Neo4jService("bolt://neo4j:7687", "neo4j", "password")

  val apiRoutes = new ApiRoutes(neo4jService).route
  val swaggerRoutes = new SwaggerDocService(system).routes

  val allRoutes = apiRoutes ~ swaggerRoutes

  val bindingFuture = Http().newServerAt("0.0.0.0", 8080).bind(allRoutes)

  println(s"Swagger JSON available at http://localhost:8080/api-docs/")

  bindingFuture.onComplete {
    case Success(binding) =>
      println(s"Server is listening on ${binding.localAddress}")
    case Failure(ex) =>
      println(s"Binding failed: ${ex.getMessage}")
      system.terminate()
  }
}
