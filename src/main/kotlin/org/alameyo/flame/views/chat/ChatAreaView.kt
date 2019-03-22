package org.alameyo.flame.views.chat

import javafx.event.EventHandler
import javafx.scene.control.Tab
import org.alameyo.flame.models.ChatModel
import tornadofx.*

class ChatAreaView : View() {

    override val root = tabpane {
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

    fun addChatTab(chatModel: ChatModel) {
        val tab = Tab(chatModel.corespondent)
        tab.onClosed = EventHandler { chatModel.isOpen = false }
        root.tabs.add(tab)
    }
}
