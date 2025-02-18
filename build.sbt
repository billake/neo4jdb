ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.16"

lazy val root = (project in file("."))
  .settings(
    name := "neo4jdb",
    idePackagePrefix := Some("com.inno.hackaton2025")
  )

libraryDependencies ++= Seq(
  "org.neo4j.driver" % "neo4j-java-driver" % "5.12.0",
  "io.circe" %% "circe-core" % "0.14.5",
  "io.circe" %% "circe-parser" % "0.14.5",
  "io.circe" %% "circe-generic" % "0.14.5"
)