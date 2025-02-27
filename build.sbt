ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.16"

lazy val root = (project in file("."))
  .settings(
    name := "neo4jdb",
    idePackagePrefix := Some("com.inno.hackaton2025")
  )

libraryDependencies ++= Seq(
  "org.neo4j.driver" % "neo4j-java-driver" % "4.4.18",
  "com.typesafe.akka" %% "akka-http" % "10.2.9",
  "com.typesafe.akka" %% "akka-stream" % "2.6.19",
  "io.circe" %% "circe-core" % "0.14.5",
  "io.circe" %% "circe-parser" % "0.14.5",
  "io.circe" %% "circe-generic" % "0.14.5"
)