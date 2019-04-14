package org.alameyo.flame.views.home.muc

import org.alameyo.flame.controllers.chat.ChatStanzaListener
import org.alameyo.flame.css.FlameStyle
import org.alameyo.flame.models.FlameRosterEntry
import org.alameyo.flame.views.home.chat.ChatEntryView
import org.alameyo.flame.views.home.chat.ChatTab
import org.jivesoftware.smack.packet.Message
import tornadofx.*

class MultiUserChatTab(flameRosterEntry: FlameRosterEntry) : ChatTab(flameRosterEntry) {

    init {
        ChatStanzaListener(this)
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
    
    override fun processIncomingMessage(stanza: Message) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}