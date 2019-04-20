package org.alameyo.flame.views.home.muc

import org.alameyo.flame.controllers.muc.MultiUserChatController
import org.alameyo.flame.controllers.muc.MultiUserChatStanzaListener
import org.alameyo.flame.css.FlameStyle
import org.alameyo.flame.models.FlameContactEntry
import org.alameyo.flame.views.home.chat.ChatEntryView
import org.alameyo.flame.views.home.chat.ChatTab
import org.jivesoftware.smack.packet.Message
import tornadofx.*

class MultiUserChatTab(flameContactEntry: FlameContactEntry) : ChatTab(flameContactEntry) {
    private val multiUserChatController = MultiUserChatController(this)

    init {
        add(
                vbox {
                    addClass(FlameStyle.chatVboxStyle)
                    chatBox = scrollpane {
                        fitToWidthProperty().value = true
                        addClass(FlameStyle.chatScrollPaneStyle)
                        vbox {}
                    }
                    promptTextField = textfield {
                        promptText = "Send the message"
                        addClass(FlameStyle.chatTextFieldStyle)
                        action {
                            sendMessage()
                        }
                    }
                })
    }

    private fun sendMessage() {
        multiUserChatController.send(promptTextField.text)
        promptTextField.clear()
    }

    private fun addEntry(chatEntry: ChatEntryView) {
        runAsync {
            chatEntriesList.add(chatEntry)
        } ui {
            chatBox.content += chatEntry
            runLater(0.5.seconds) {
                chatBox.vvalue = 1.0
            }
        }
    }

    override fun processIncomingMessage(stanza: Message) = addEntry(ChatEntryView(stanza.from.resourceOrEmpty.toString(), stanza.body))
}