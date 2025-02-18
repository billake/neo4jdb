package com.inno.hackaton2025

import org.neo4j.driver.{AuthTokens, Driver, GraphDatabase, Session}

object Neo4jConnector {

  object Neo4jConnector {
    val uri = "bolt://localhost:7687"
    val user = "neo4j"
    val password = "password"

    private val driver: Driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password))

    def getSession: Session = driver.session()

    def close(): Unit = driver.close()
  }


}
