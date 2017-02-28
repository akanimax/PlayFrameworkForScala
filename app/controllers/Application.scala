package controllers

import play.api.Play
import play.api.mvc._
import play.api.Play.current

object Application extends Controller {
  def index = Action {
    Ok(views.html.index())
  }

  def imageAt(image: String) = Action {
    Ok.sendFile(Play.getFile("public/images/media/" + image))
  }
}
