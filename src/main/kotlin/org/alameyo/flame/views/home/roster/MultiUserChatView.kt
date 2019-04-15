package org.alameyo.flame.views.home.roster

import org.alameyo.flame.controllers.BookmarksController
import org.alameyo.flame.controllers.chat.ChatAreaController
import org.alameyo.flame.css.FlameStyle
import org.alameyo.flame.models.FlameContactEntry
import tornadofx.*

class MultiUserChatView : View() {

    private val bookmarksController: BookmarksController by inject()
    private val chatAreaController: ChatAreaController by inject()

    override val root = scrollpane {
        vbox {
            bookmarksController.bookmarks().forEach {
                val flameRosterEntry = FlameContactEntry(it)
                chatAreaController.addMuc(flameRosterEntry)
                hbox {
                    addClass(FlameStyle.rosterList)
                    button(it.name ?: it.jid.toString()) {
                        addClass(FlameStyle.roundButton)
                        action {
                            chatAreaController.openChatWithRosterEntry(flameRosterEntry)
                        }
                    }
                }
            }
        }
    }
}