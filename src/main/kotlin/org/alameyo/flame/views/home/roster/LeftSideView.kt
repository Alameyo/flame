package org.alameyo.flame.views.home.roster

import javafx.scene.control.TabPane
import tornadofx.View
import tornadofx.tab
import tornadofx.tabpane

class LeftSideView : View() {

    private val rosterView: RosterView by inject()
    private val bookmarksView: BookmarksView by inject()

    override val root = tabpane {
        tab("Friends") {
            content = rosterView.root
        }

        tab("Rooms") {
            content = bookmarksView.root
        }

        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
    }
}
