package com.inno.hackaton2025

import com.inno.hackaton2025.Neo4jConnector._

object Main {
  def main(args: Array[String]): Unit = {
    val interviewData = JsonParser.loadJson("src/main/resources/json/interview_data.json")
    interviewData.foreach(Neo4jImporter.importData)
    Neo4jConnector.close()
  }
}
