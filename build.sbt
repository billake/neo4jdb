
ThisBuild / version := "1.0.1"

ThisBuild / scalaVersion := "2.13.16"

lazy val root = (project in file("."))
  .settings(
    name := "neo4jdb",
    idePackagePrefix := Some("com.inno.hackaton2025")
  )

val swaggerDependencies = Seq(
  "jakarta.ws.rs" % "jakarta.ws.rs-api" % "3.0.0",
  "com.github.swagger-akka-http" %% "swagger-akka-http-with-ui" % "2.6.0",
  "com.github.swagger-akka-http" %% "swagger-scala-module" % "2.5.0",
  "com.github.swagger-akka-http" %% "swagger-enumeratum-module" % "2.3.0",
  "io.swagger.core.v3" % "swagger-jaxrs2-jakarta" % "2.1.11"
)

libraryDependencies ++= Seq(
  "org.neo4j.driver" % "neo4j-java-driver" % "4.4.18",
  "com.typesafe.akka" %% "akka-http" % "10.2.9",
  "com.typesafe.akka" %% "akka-stream" % "2.6.19",
  "io.circe" %% "circe-core" % "0.14.5",
  "io.circe" %% "circe-parser" % "0.14.5",
  "io.circe" %% "circe-generic" % "0.14.5",
  "eu.michael-simons.neo4j" % "neo4j-migrations" % "2.13.2"
) ++ swaggerDependencies

assembly / assemblyJarName  := "neo4jdb-app.jar"
assembly / assemblyMergeStrategy := {
  case PathList("reference.conf") => MergeStrategy.concat
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}
