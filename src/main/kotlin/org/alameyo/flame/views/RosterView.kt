package org.alameyo.flame.views

import javafx.scene.control.TabPane
import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.css.FlameStyle
import tornadofx.*

class RosterView: View() {

    private val flameController: FlameController by inject()
    private val rosterController = flameController.rosterController

    override val root = tabpane {
        tab("Friends") {
            scrollpane {
                vbox {
                    rosterController.entries().forEach {
                        hbox {
                            addClass(FlameStyle.rosterList)
                            button(it.name ?: it.jid.toString()) {
                                addClass(FlameStyle.roundButton)
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