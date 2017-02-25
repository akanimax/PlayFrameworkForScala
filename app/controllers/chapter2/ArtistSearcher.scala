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

  //  universal search method
  def search(name: Option[String], country: Option[String], and: Boolean) = Action {
    val result = (name, country) match {
      case (Some(n), Some(c)) if and => Artists.fetchByNameAndCountry(n, c)
      case (Some(n), Some(c)) => Artists.fetchByNameOrCountry(n, c)
      case (None, Some(c)) => Artists.fetchByCountry(c)
      case (Some(n), None) => Artists.fetchByName(n)
      case (None, None) => Vector() // empty vector
    }

    if(result.nonEmpty) Ok(views.html.chapter2_views.found_artists(result))
    else NoContent
  }
}
