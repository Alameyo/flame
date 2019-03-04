package org.alameyo.flame.views

import javafx.scene.control.TabPane
import org.alameyo.flame.controllers.FlameController
import org.jivesoftware.smack.roster.RosterEntry
import tornadofx.*

class FlameApplicationView : View() {
    private val flameController: FlameController by inject()
    private val rosterController = flameController.rosterController

    override val root = borderpane {
        top {

        }
        left {
            tabpane {
                tab("Friends") {
                    tableview<RosterEntry> {
                        items = rosterController.entries().toList().observable()
                        column("Name", RosterEntry::getName)
                        column("Presence", RosterEntry::getJid)
                    }
                }

                tab("Rooms") {

                }
                tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
            }
        }
        center {

        }
        bottom {

        }
    }
}