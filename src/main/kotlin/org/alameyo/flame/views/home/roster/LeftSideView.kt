package org.alameyo.flame.views.home.roster

import javafx.scene.control.TabPane
import org.alameyo.flame.controllers.chat.ChatAreaController
import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.css.FlameStyle
import org.alameyo.flame.models.FlameRosterEntry
import tornadofx.*

class LeftSideView : View() {

    override val root = tabpane {
        tab(RosterView::class) {
            text = "Friends"
        }

        tab("Rooms") {

        }

        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
    }
}
