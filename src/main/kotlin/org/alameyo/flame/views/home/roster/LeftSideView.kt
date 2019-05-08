package org.alameyo.flame.views.home.roster

import javafx.scene.control.TabPane
import org.alameyo.flame.css.FlameStyle
import org.alameyo.flame.css.FlameStyle.Companion.leftSideTab
import tornadofx.View
import tornadofx.addClass
import tornadofx.tab
import tornadofx.tabpane

class LeftSideView : View() {

    private val rosterView: RosterView by inject()
    private val multiUserChatView: MultiUserChatView by inject()

    override val root = tabpane {
        tab("Friends") {
            addClass(FlameStyle.scrollPaneStyle)
            content = rosterView.root
        }

        tab("Rooms") {
            content = multiUserChatView.root
        }

        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
    }
}
