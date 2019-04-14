package org.alameyo.flame.views.home.chat

import tornadofx.View
import tornadofx.runLater
import tornadofx.tabpane

class ChatAreaView : View() {

    override val root = tabpane()

    fun addChatTab(chatTab: ChatTab) {
        runLater {
            root.tabs.add(chatTab)
        }
    }
}
