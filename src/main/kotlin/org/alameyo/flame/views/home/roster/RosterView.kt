package org.alameyo.flame.views.home.roster

import javafx.scene.layout.VBox
import org.alameyo.flame.models.FlameContactEntry
import org.alameyo.flame.views.home.roster.ContactsView.StateEnum.*
import org.jivesoftware.smack.roster.RosterEntry
import tornadofx.clear

class RosterView : ContactsView() {

    override val addContactUrl = "UI/Friends.png"
    override val addContactUrlActive = "UI/Friends_Active.png"

    override fun populateScrollPane(box: VBox) {
        box.clear()
        rosterController.entries().forEach {
            box.add(rosterBox(it))
        }
    }

    private fun rosterBox(rosterEntry: RosterEntry) = rosterBoxFromEntry(FlameContactEntry(rosterEntry))

    override fun removeContact(flameContactEntry: FlameContactEntry) {
        runAsync {
            rosterController.removeContactFromRoster(flameContactEntry)
        } ui {
            populateScrollPane(holdBox)
        }
    }

    override fun replaceAction() {
        when (currentState) {
            CONTACT_VIEW -> runAsync { currentState = ROSTER_VIEW } ui { contactView.replaceWith<AddContactView>() }
            BOOKMARKS_VIEW -> runAsync { currentState = ROSTER_VIEW } ui {
                find<AddBookmarkView>().replaceWith<AddContactView>()
            }
        }
    }

    override fun addChatOrMuc(flameContactEntry: FlameContactEntry) {
        chatAreaController.addChat(flameContactEntry)
    }
}
