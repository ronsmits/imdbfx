package imdbfx.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject
import tornadofx.getValue
import tornadofx.setValue

class Movie : JsonModel {

    val titleProperty = SimpleStringProperty()
    var title by titleProperty

    override fun updateModel(json: JsonObject) {
        with (json){
            title = string("title")
        }
    }
}

class MovieModel : ItemViewModel<Movie>() {
    val title = bind(Movie::titleProperty)
}

