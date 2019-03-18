package org.alameyo.flame.views

import org.alameyo.flame.controllers.FlameController
import tornadofx.*

class FlameApplicationView : View() {
    private val rosterView: RosterView by inject()
    override val root = borderpane {

        top {

        }
        left = rosterView.root

        center {
            tabpane {
                tab("Talk") {
                    vbox {
                        textarea { }
                        flowpane {
                            button("Send")
                            textfield("Type your message") { }
                        }
                    }
                }
            }
        }
    }

}