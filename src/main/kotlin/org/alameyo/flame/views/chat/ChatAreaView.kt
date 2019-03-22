package org.alameyo.flame.views.chat

import tornadofx.View
import tornadofx.tabpane

class ChatAreaView : View() {

    override val root = tabpane()

    fun addChatTab(chatTab: ChatTab) {
        root.tabs.add(chatTab)
    }
}
