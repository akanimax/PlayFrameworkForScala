package controllers

import play.api.mvc._
import play.twirl.api.Html

object Application extends Controller {

  def index = Action {
    Ok(views.html.main("The best place to create applications")
                    (Html("Animesh is the best man in the world")))
  }
}
