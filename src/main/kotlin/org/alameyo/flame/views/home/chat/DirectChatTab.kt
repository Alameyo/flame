package org.alameyo.flame.views.home.chat

import org.alameyo.flame.controllers.chat.ChatStanzaListener
import org.alameyo.flame.css.FlameStyle.Companion.chatScrollPaneStyle
import org.alameyo.flame.css.FlameStyle.Companion.chatTextFieldStyle
import org.alameyo.flame.css.FlameStyle.Companion.chatVboxStyle
import org.alameyo.flame.models.FlameContactEntry
import org.jivesoftware.smack.packet.Message
import tornadofx.*

class DirectChatTab(flameContactEntry: FlameContactEntry) : ChatTab(flameContactEntry) {

    init {
        ChatStanzaListener(this)
        add(
                vbox {
                    addClass(chatVboxStyle)
                    chatBox = scrollpane {
                        fitToWidthProperty().value = true
                        addClass(chatScrollPaneStyle)
                        vbox {}
                    }
                    promptTextField = textfield {
                        promptText = "Send the message"
                        addClass(chatTextFieldStyle)
                        action {
                            sendMessage()
                        }
                    }
                })
    }

    private fun sendMessage() {
        addEntry(ChatEntryView("ME", promptTextField.text))
        chatSender.send(promptTextField.text)
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

    override fun processIncomingMessage(stanza: Message) =
            addEntry(ChatEntryView(flameContactEntry.name ?: stanza.from.asBareJid().toString(), stanza.body))
}
