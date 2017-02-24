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

  def searchByNameOrCountry(name: Option[String], country: String, and: Boolean = false) = Action {
    val result = name match {
      case Some(n) if and => Artists.fetchByNameAndCountry(n, country)
      case Some(n) => Artists.fetchByNameOrCountry(n, country)
      case None => Artists.fetchByCountry(country)
    }

    if(result.nonEmpty) Ok(views.html.chapter2_views.found_artists(result))
    else NoContent
  }
}
