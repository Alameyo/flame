package org.alameyo.flame.views.home.muc

import org.alameyo.flame.controllers.muc.MultiUserChatStanzaListener
import org.alameyo.flame.css.FlameStyle
import org.alameyo.flame.models.FlameContactEntry
import org.alameyo.flame.views.home.chat.ChatEntryView
import org.alameyo.flame.views.home.chat.ChatTab
import org.jivesoftware.smack.packet.Message
import tornadofx.*

class MultiUserChatTab(flameContactEntry: FlameContactEntry) : ChatTab(flameContactEntry) {

    init {
        MultiUserChatStanzaListener(this)
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

    override fun processIncomingMessage(stanza: Message) = addEntry(ChatEntryView(flameContactEntry.name ?: stanza.from.asBareJid().toString(), stanza.body))
}