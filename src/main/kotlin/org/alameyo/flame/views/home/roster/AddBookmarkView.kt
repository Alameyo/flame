package org.alameyo.flame.views.home.roster

import javafx.beans.property.SimpleStringProperty
import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.controllers.muc.BookmarksController
import org.alameyo.flame.css.FlameStyle
import org.alameyo.flame.views.fitSize
import org.jxmpp.jid.impl.JidCreate.bareFrom
import tornadofx.*

class AddBookmarkView : View() {

    private val bookmarksController: BookmarksController by inject()
    private val bookmarksView = find<BookmarksView>()

    private val jid = SimpleStringProperty()
    private val name = SimpleStringProperty()

    override val root = scrollpane {
        hbox {
            form {
                fieldset {
                    field("Bookmark JID") {
                        textfield(jid)
                    }
                    field("Name") {
                        textfield(name)
                    }
                    button {
                        addClass(FlameStyle.littleRoundButton)
                        imageview("UI/Rooms.png").fitSize(this)
                        action {
                            if (validateInput()) {
                                runAsync {
                                    bookmarksController.addBookmark(bareFrom(jid.value), name.value)
                                } ui {
                                    bookmarksView.populateScrollPane(bookmarksView.holdBox)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun validateInput() = !jid.value.isNullOrEmpty()
}