package imdbfx.model

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject
import tornadofx.getValue
import tornadofx.setValue
import java.net.URL

interface Base : JsonModel {
    fun title() : SimpleStringProperty
    fun thumbnail() : SimpleStringProperty
}

class BaseModel : ItemViewModel<Base>() {
    val title = bind(Base::title)
    val thumbnail = bind(Base::thumbnail)
}

class Movie : Base {
    override fun title() = titleProperty

    override fun thumbnail() = thumbnailProperty

    val titleProperty = SimpleStringProperty()
    var title by titleProperty
    val thumbnailProperty = SimpleStringProperty()
    var thumbnail by thumbnailProperty


    override fun updateModel(json: JsonObject) {
        with (json){
            title = string("title")
           val imageholder = getJsonObject("poster")
            println(imageholder)
            thumbnail = imageholder.getString("thumb")
            if(thumbnail.isBlank()) thumbnail="https://dummyimage.com/200"
        }
    }

    override fun toString(): String {
        return "Movie(titleProperty=$titleProperty, thumbnailProperty=$thumbnailProperty)"
    }
}

class Actor : Base {
    override fun title() = nameProperty
    override fun thumbnail() = thumbnailProperty

    val nameProperty = SimpleStringProperty()
    var name by nameProperty
    val thumbnailProperty = SimpleStringProperty()
    var thumbnail by thumbnailProperty

    override fun updateModel(json: JsonObject) {
        with(json){
            name = string("title")
            val imageholder = getJsonObject("image")
            thumbnail = imageholder.string("thumb")
            if(thumbnail.isBlank()) thumbnail="https://dummyimage.com/200"
        }
    }

}