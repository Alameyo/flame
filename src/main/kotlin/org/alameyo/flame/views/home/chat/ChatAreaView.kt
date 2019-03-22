package org.alameyo.flame.views.home.chat

import tornadofx.View
import tornadofx.tabpane

class ChatAreaView : View() {

    override val root = tabpane()

    fun addChatTab(chatTab: ChatTab) {
        root.tabs.add(chatTab)
    }
}
