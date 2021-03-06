package imdbfx.view

import imdbfx.app.ImdbController
import imdbfx.model.Base
import imdbfx.model.BaseModel
import imdbfx.model.Movie
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    override val root = vbox {
        add(SearchView::class)
        add(DatagridView::class)
    }
}

val list = FXCollections.observableArrayList<Base>()
class SearchView : View() {

    val imdb: ImdbController by inject()
    //    val formatter = object:  StringConverter<Integer>() {
//        override fun toString(value: Integer?): String {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//    }
    override val root = form {
        val model = ViewModel()
        val moviename = model.bind { SimpleStringProperty() }
        val actorname = model.bind { SimpleStringProperty() }
        val year = model.bind { SimpleIntegerProperty() }
        fieldset("search") {
            field("movie") { textfield(moviename).enableWhen(actorname.isBlank()) }
            field("actor") { textfield(actorname).enableWhen(moviename.isBlank()) }
            field("year") { textfield(year) }
            buttonbar {
                button("lookup") {
                    isDefaultButton = true
                    action {
                        runAsyncWithProgress {
                            if(moviename.isNotBlank().value)
                                imdb.getMovie(moviename.value, year.value.toInt())
                            else
                                imdb.getActor(actorname.value)
                        } ui {
                            println("it is set to $it")
                            list.setAll(it)
                            println("list size is ${list.size}")
                        }
                    }
                }
            }
        }
    }
}


class DatagridView : View() {
    override val root = datagrid<Base>(list) {
        setPrefSize(550.0, 550.0)
        cellWidth=300.0
        cellHeight=300.0
        cellFragment<BaseCellFragment>()
    }
}

class BaseCellFragment : DataGridCellFragment<Base>(){
    val basemodel = BaseModel().bindTo(this)
    override val root = vbox {
        println(basemodel.thumbnail)
        imageview(basemodel.thumbnail, false)
        label(basemodel.title)
    }

}