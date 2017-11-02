package imdbfx.model

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject
import tornadofx.getValue
import tornadofx.setValue
import java.net.URL

class Movie : JsonModel {

    val titleProperty = SimpleStringProperty()
    var title by titleProperty
    val thumbnailProperty = SimpleStringProperty()
    var thumbnail by thumbnailProperty


    override fun updateModel(json: JsonObject) {
        with (json){
            title = string("title")
           val imageholder = getJsonObject("poster")?: getJsonObject("image")
            println(imageholder)
            thumbnail = imageholder.getString("thumb")
            if(thumbnail.isBlank()) thumbnail="https://dummyimage.com/200"
        }
    }

    override fun toString(): String {
        return "Movie(titleProperty=$titleProperty, thumbnailProperty=$thumbnailProperty)"
    }


}

class MovieModel : ItemViewModel<Movie>() {
    val title = bind(Movie::titleProperty)
    val thumbnail = bind(Movie::thumbnailProperty)
}

