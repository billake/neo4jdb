package com.inno.hackaton2025
package swagger

import akka.actor.ActorSystem
import com.github.swagger.akka.model.Info
import com.github.swagger.akka.ui.SwaggerHttpWithUiService
import com.inno.hackaton2025.api.ApiRoutes

class SwaggerDocService(system: ActorSystem) extends SwaggerHttpWithUiService {

  override def apiClasses: Set[Class[_]] =  Set(classOf[ApiRoutes])
  override val host = "localhost:8080"
  override val apiDocsPath = "api-docs"
  override val info = Info(version = "1.0", title = "Hackathon API", description = "API documentation for Hackathon 2025")

}

