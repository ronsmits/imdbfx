package imdbfx.view

import javafx.beans.property.IntegerProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    override val root = vbox {
        this += find(SearchView::class).root
    }
}


class SearchView : View() {
    override val root = form {
        val model = ViewModel()
        val moviename = model.bind { SimpleStringProperty() }
        val actorname = model.bind { SimpleStringProperty() }
        val year = model.bind { SimpleIntegerProperty() }
        fieldset("search") {
            field("movie") { textfield(moviename).enableWhen(actorname.isBlank()) }
            field("actor") { textfield(actorname).enableWhen(moviename.isBlank()) }
            field("year") { textfield(year) }
        }
    }
}


