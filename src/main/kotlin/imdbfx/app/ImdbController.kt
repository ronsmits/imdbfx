package imdbfx.app

import imdbfx.model.Movie
import tornadofx.*

class ImdbController: Controller() {
    val api : Rest by inject()
    init {
        api.baseURI = "http://theimdbapi.org/api/find"
    }
    fun getMovie(title: String, year : Int): List<Movie> {
        val params = mutableMapOf("title" to title)
        if(year > 1800) params.put("year", year.toString())
        println(params.queryString)
        println(api.getURI("movie${params.queryString}"))
        val resp : Rest.Response? = try {api.get("movie${params.queryString}")} catch (ex: RestException) { null}
        val list = resp?.list()?.toModel()?: emptyList<Movie>()
        println(list)
        return list
    }
}