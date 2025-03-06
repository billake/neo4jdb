package com.inno.hackaton2025

import ac.simons.neo4j.migrations.core._
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.Materializer
import com.inno.hackaton2025.api._
import com.inno.hackaton2025.service.Neo4jService
import com.inno.hackaton2025.swagger.SwaggerDocService
import akka.http.scaladsl.server.Directives._
import org.neo4j.driver.{AuthTokens, Driver, GraphDatabase}

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

  println("Connecting to Neo4j...")
  val driver: Driver = GraphDatabase.driver(
    "bolt://neo4j:7687",
    AuthTokens.basic("neo4j", "password")
  )
  println("Connected to Neo4j.")

  val migrationsConfig = MigrationsConfig.builder()
    .withLocationsToScan("classpath:neo4j/migrations", "classpath:sb")
    .build()

  val migrations = new Migrations(migrationsConfig, driver)

  println("Applying migrations...")
  val migrationResult = Try(migrations.apply())

  migrationResult match {
    case Success(_) =>
      println("Migrations applied successfully!")
    case Failure(ex) =>
      println(s"Failed to apply migrations: ${ex.getMessage}")
      ex.printStackTrace()
      system.terminate()
      System.exit(1)
  }

  sys.addShutdownHook {
    driver.close()
    println("Neo4j driver closed.")
  }
}
