package com.inno.hackaton2025.util

object CypherUtils {
  def escapeCypherString(value: String): String = value.replace("'", "\\'")
}

