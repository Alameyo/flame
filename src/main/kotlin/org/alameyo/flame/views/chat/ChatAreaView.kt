package org.alameyo.flame.views.chat

import javafx.event.EventHandler
import javafx.scene.control.Tab
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

    fun addChatTab(chatTab: ChatTab) {
        root.tabs.add(chatTab)
    }

//    fun createTabl() {
//        tab
//    }
}
