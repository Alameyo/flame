package org.alameyo.flame.views.home.roster

import javafx.scene.control.TabPane
import org.alameyo.flame.controllers.ChatAreaController
import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.css.FlameStyle
import org.alameyo.flame.models.FlameRosterEntry
import tornadofx.*

class RosterView : View() {

    private val flameController: FlameController by inject()
    private val rosterController = flameController.rosterController
    private val chatAreaController: ChatAreaController by inject()

    override val root = tabpane {
        tab("Friends") {
            scrollpane {
                vbox {
                    rosterController.entries().forEach {
                        val flameRosterEntry = FlameRosterEntry(it)
                        chatAreaController.addChat(flameRosterEntry)
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

        tab("Rooms") {

        }

        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
    }
}