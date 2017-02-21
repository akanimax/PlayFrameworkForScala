package controllers.chapter2

import Models.chapter2_models.Artists
import play.api.mvc.{Action, Controller}

/**
  * Created by botman on 21/2/17.
  */
object ArtistSearcher extends Controller{
  def home = Action {
    Redirect(routes.ArtistSearcher.allArtists())
  }

  def allArtists = Action {
    Ok(views.html.chapter2_views.artist_shower(Artists.fetchAllArtists))
  }

  def searchByName(name: String) = Action {
    Ok(views.html.chapter2_views.found_artists(Artists.fetchByName(name)))
  }
}
