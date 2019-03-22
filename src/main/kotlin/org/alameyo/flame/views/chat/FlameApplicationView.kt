package org.alameyo.flame.views.chat

import tornadofx.*

class FlameApplicationView : View() {
    private val rosterView: RosterView by inject()
    private val chatAreView: ChatAreaView by inject()
    override val root = borderpane {

        top {

        }
        left = rosterView.root

        center = chatAreView.root
    }
}
