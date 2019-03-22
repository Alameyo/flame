package org.alameyo.flame.views.home

import org.alameyo.flame.views.home.chat.ChatAreaView
import org.alameyo.flame.views.home.contact.ContactView
import org.alameyo.flame.views.home.roster.RosterView
import tornadofx.*

class FlameApplicationView : View() {
    private val rosterView: RosterView by inject()
    private val chatAreView: ChatAreaView by inject()
    private val rightView: ContactView by inject()
    override val root = borderpane {

        top {

        }
        left = rosterView.root

        center = chatAreView.root

        right = rightView.root
    }
}
