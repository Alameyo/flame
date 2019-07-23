package org.alameyo.flame.views.home.roster

import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.controllers.chat.ChatAreaController
import org.alameyo.flame.controllers.chat.RosterController
import org.alameyo.flame.controllers.muc.BookmarksController
import org.alameyo.flame.css.FlameStyle.Companion.rosterList
import org.alameyo.flame.css.FlameStyle.Companion.roundButton
import org.alameyo.flame.models.FlameContactEntry
import org.alameyo.flame.views.fitSize
import org.alameyo.flame.views.home.contact.ContactView
import org.alameyo.flame.views.home.roster.ContactsView.StateEnum.CONTACT_VIEW
import tornadofx.*

abstract class ContactsView : View() {
    protected val rosterController: RosterController by inject() //rosterController needs rework as it works weird now anda can't be in subclass
    protected val bookmarksController: BookmarksController by inject() //it might be TornadoFX problem that controllers doesn't work well in inherited view
    protected val chatAreaController: ChatAreaController by inject()
    protected val contactView: ContactView by inject()
    lateinit var holdBox: VBox
    protected abstract val addContactUrl: String
    protected abstract val addContactUrlActive: String

    override val root = vbox {
        add(addUserBox())
        scrollpane {
            holdBox = vbox {
                populateScrollPane(this)
            }
        }
    }

    abstract fun populateScrollPane(box: VBox)

    private fun addUserBox(): HBox {
        return hbox {
            addClass(rosterList)
            runAsync { } ui {
                button {
                    var image = imageview(addContactUrl)
                    image.fitSize(this)
                    addClass(roundButton)
                    action {
                        replaceAction()
                        image = imageview(addContactUrlActive)
                        image.fitSize(this)
                    }
                }
            }
        }
    }

    protected fun rosterBoxFromEntry(flameRosterEntry: FlameContactEntry): HBox {
        addChatOrMuc(flameRosterEntry)
        return hbox {
            addClass(rosterList)
            button(flameRosterEntry.name ?: flameRosterEntry.jid) {
                addClass(roundButton)
                action {
                    chatAreaController.openChatWithRosterEntry(flameRosterEntry)
                }
                contextmenu {
                    item("Remove contact").action {
                        removeContact(flameRosterEntry)
                    }
                }

            }
        }
    }

    protected abstract fun removeContact(flameContactEntry: FlameContactEntry)

    protected abstract fun replaceAction()

    protected abstract fun addChatOrMuc(flameContactEntry: FlameContactEntry)

    protected companion object State {
        var currentState = CONTACT_VIEW
    }

    protected enum class StateEnum {
        CONTACT_VIEW,
        ROSTER_VIEW,
        BOOKMARKS_VIEW
    }
}