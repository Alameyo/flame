package org.alameyo.flame.views.home.chat

import javafx.event.EventHandler
import javafx.scene.control.ScrollPane
import javafx.scene.control.Tab
import javafx.scene.control.TextField
import org.alameyo.flame.controllers.chat.ChatMessageSender
import org.alameyo.flame.controllers.chat.ChatStanzaListener
import org.alameyo.flame.css.FlameStyle.Companion.chatScrollPaneStyle
import org.alameyo.flame.css.FlameStyle.Companion.chatTextFieldStyle
import org.alameyo.flame.css.FlameStyle.Companion.chatVboxStyle
import org.alameyo.flame.models.FlameRosterEntry
import org.jivesoftware.smack.packet.Message
import tornadofx.*

class ChatTab(val flameRosterEntry: FlameRosterEntry) : Tab(flameRosterEntry.name ?: flameRosterEntry.jid ?: throw ChatTabWithoutNameException()) {

    var isOpen = false
    private val chatEntriesList = mutableListOf<ChatEntryView>()
    private lateinit var chatBox: ScrollPane
    private lateinit var promptTextField: TextField
    private val chatSender = ChatMessageSender(flameRosterEntry)

    init {
        ChatStanzaListener(this)
        onClosed = EventHandler { isOpen = false }
        add(
            vbox {
                addClass(chatVboxStyle)
                chatBox = scrollpane {
                    fitToWidthProperty().value = true
                    addClass(chatScrollPaneStyle)
                    vbox {

                    }
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

    fun processIncomingMessage(stanza: Message) =
        addEntry(ChatEntryView(flameRosterEntry.name ?: stanza.from.asBareJid().toString(), stanza.body))

    private class ChatTabWithoutNameException : Exception("Created chat tab have no name")
}