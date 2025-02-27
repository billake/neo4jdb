package com.inno.hackaton2025

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.Materializer
import com.inno.hackaton2025.api._
import com.inno.hackaton2025.service.Neo4jService

import scala.concurrent.ExecutionContext
import scala.io.StdIn

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("Neo4jImportSystem")
  implicit val materializer: Materializer = Materializer(system)
  implicit val executionContext: ExecutionContext = system.dispatcher

  val neo4jService = new Neo4jService("bolt://localhost:7687", "neo4j", "password")
  val routes = new ApiRoutes(neo4jService).route

  val bindingFuture = Http().newServerAt("localhost", 8080).bind(routes)

  println(s"Server running at http://localhost:8080/")
  println("Press ENTER to stop...")
  StdIn.readLine()
  bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
  neo4jService.close()
}
