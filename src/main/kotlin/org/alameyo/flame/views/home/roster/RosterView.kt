package org.alameyo.flame.views.home.roster

import javafx.beans.binding.Bindings.and
import javafx.scene.paint.Color
import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.controllers.chat.ChatAreaController
import org.alameyo.flame.css.FlameStyle
import org.alameyo.flame.css.FlameStyle.Companion.leftSideTab
import org.alameyo.flame.css.FlameStyle.Companion.rosterList
import org.alameyo.flame.css.FlameStyle.Companion.roundButton
import org.alameyo.flame.css.FlameStyle.Companion.scrollPaneStyle
import org.alameyo.flame.models.FlameContactEntry
import tornadofx.*
import java.awt.SystemColor.scrollbar

class RosterView : View() {

    private val flameController: FlameController by inject()
    private val rosterController = flameController.rosterController
    private val chatAreaController: ChatAreaController by inject()

    override val root = scrollpane {
        addClass(scrollPaneStyle)
        vbox {
            addClass(leftSideTab)
            rosterController.entries().forEach {
                val flameRosterEntry = FlameContactEntry(it)
                chatAreaController.addChat(flameRosterEntry)
                hbox {
                    addClass(rosterList)
                    button(it.name ?: it.jid.toString()) {
                        addClass(roundButton)
                        action {
                            chatAreaController.openChatWithRosterEntry(flameRosterEntry)
                        }
                    }
                }
            }
        }
    }
}
