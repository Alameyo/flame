package org.alameyo.flame.views.chat

import javafx.scene.Parent
import tornadofx.*

class FlameApplicationView : View() {
    private val rosterView: RosterView by inject()
    private val chatAreView: ChatAreaView by inject()
    private val rightView: RightView by inject()
    override val root = borderpane {

        top {

        }
        left = rosterView.root

        center = chatAreView.root

        right = rightView.root
    }
}

class RightView: View() {
    override val root = hbox {
        button("ELOOOOO") {  }
    }
}