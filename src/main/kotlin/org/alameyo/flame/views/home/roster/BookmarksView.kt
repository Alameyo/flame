package org.alameyo.flame.views.home.roster

import javafx.scene.layout.VBox
import org.alameyo.flame.models.FlameContactEntry
import org.alameyo.flame.views.home.roster.ContactsView.StateEnum.*
import org.jivesoftware.smackx.bookmarks.BookmarkedConference
import tornadofx.clear

class BookmarksView : ContactsView() {

    override val addContactUrl = "UI/Rooms.png"
    override val addContactUrlActive = "UI/Rooms_Active.png"

    override fun populateScrollPane(box: VBox) {
        box.clear()
        bookmarksController.bookmarks().forEach {
            box.add(rosterBox(it))
        }
    }

    private fun rosterBox(rosterEntry: BookmarkedConference) = rosterBoxFromEntry(FlameContactEntry(rosterEntry))

    override fun removeContact(flameContactEntry: FlameContactEntry) {
        runAsync {
            bookmarksController.bookmarks()
        } ui {
            populateScrollPane(holdBox)
        }
    }

    override fun replaceAction() {
        when (currentState) {
            CONTACT_VIEW -> runAsync {currentState = BOOKMARKS_VIEW} ui { contactView.replaceWith<AddBookmarkView>() }
            ROSTER_VIEW -> runAsync { currentState = BOOKMARKS_VIEW } ui {
                find<AddContactView>().replaceWith<AddBookmarkView>()
            }
        }
    }

    override fun addChatOrMuc(flameContactEntry: FlameContactEntry) {
        chatAreaController.addMuc(flameContactEntry)
    }
}