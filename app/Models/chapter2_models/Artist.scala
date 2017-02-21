package Models.chapter2_models

/**
  * Created by botman on 21/2/17.
  */
case class Artist(name: String, country: String)

object Artists {
  private val availableArtists: Vector[Artist] = Vector(
    Artist("Wolfgang Amadeus Mozert", "Austria"),
    Artist("Ludwig van Beethoven", "Germany"),
    Artist("Johann Sebastian Bach", "Germany"),
    Artist("Frederic Francois Chopin", "Poland"),
    Artist("Joseph Haydn", "Austria"),
    Artist("Antonio Lucio Vivaldi", "Italy"),
    Artist("Franz Peter Schubert", "Austria"),
    Artist("Franz Liszt", "Austria"),
    Artist("Giuseppe Fortunino Francesco Verdi", "Austria")
  )

  def fetchByName(name: String): Vector[Artist] =
    availableArtists.filter(artist => artist.name.contains(name))

  def fetchByCountry(country: String): Vector[Artist] =
    availableArtists.filter(artist => artist.name.contains(country))

  def fetchByNameOrCountry(name: String, country: String): Vector[Artist] =
    availableArtists.filter(artist =>
      artist.name.contains(name) || artist.country.contains(country))

  def fetchByNameAndCountry(name: String, country: String): Vector[Artist] =
    availableArtists.filter(artist =>
      artist.name.contains(name) && artist.country.contains(country))

  def fetchAllArtists: Vector[Artist] = availableArtists
}
