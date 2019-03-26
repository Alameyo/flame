package org.alameyo.flame.views.home.chat

import javafx.event.EventHandler
import javafx.scene.control.ScrollPane
import javafx.scene.control.Tab
import javafx.scene.paint.Color.*
import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.models.ChatEntry
import org.alameyo.flame.models.FlameRosterEntry
import org.jivesoftware.smack.StanzaListener
import tornadofx.*
import java.lang.Exception
import org.jivesoftware.smack.filter.FromMatchesFilter
import org.jivesoftware.smack.filter.StanzaTypeFilter
import org.jivesoftware.smack.filter.AndFilter
import org.jivesoftware.smack.filter.StanzaFilter
import org.jivesoftware.smack.filter.StanzaTypeFilter.*
import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.packet.Stanza
import org.jivesoftware.smack.tcp.XMPPTCPConnection


class ChatTab(val flameRosterEntry: FlameRosterEntry) : Tab(flameRosterEntry.name ?: flameRosterEntry.jid ?: throw ChatTabWithoutNameException()) {

    var isOpen = false
    private val chatEntriesList = mutableListOf<ChatEntry>()
    private lateinit var chatBox: ScrollPane

    init {
        ChatStanzaListener(this)
        onClosed = EventHandler { isOpen = false }
        add(
            vbox {
                style {
                    backgroundColor += c("#27324E")
                    prefHeight = 500.px
                    maxHeight = 500.px
                    prefWidth = 600.px
                }
                chatBox = scrollpane {
                    fitToWidthProperty().value = true
                    style {
                        baseColor = c("#27324E")
                        backgroundColor += TRANSPARENT
                        prefHeight = 500.px
                        maxHeight = 500.px
                        prefWidth = 600.px

                    }
                    vbox {

                    }
                }
                textfield {
                    promptText = "Send the message"
                    style {
                        prefWidth = 600.px
                    }
                    action {
                        chatBox.content += ChatEntry("ME", text)
                        text = ""
                        runLater(0.5.seconds) {
                            chatBox.vvalue = 1.0
                        }
                    }
                }
            })
    }

    private fun addEntry(chatEntry: ChatEntry) {
        runAsync {
            chatEntriesList.add(chatEntry)
        } ui {
            chatBox.content += chatEntry
        }
    }

    fun processIncomingMessage(stanza: Message) =
        addEntry(ChatEntry(flameRosterEntry.name ?: stanza.from.asBareJid().toString(), stanza.body))

    private class ChatTabWithoutNameException : Exception("Created chat tab have no name")

    private class ChatStanzaListener(val chatTab: ChatTab) : StanzaListener {

        private val connection = find<FlameController>().connection
        private val filter: StanzaFilter = AndFilter(MESSAGE, FromMatchesFilter.create(chatTab.flameRosterEntry.rosterEntry.jid))

        init {
            connection.addAsyncStanzaListener(this, filter)
        }

        override fun processStanza(packet: Stanza?) {
            when (packet) {
                is Message -> if(packet.body!=null) {
                    chatTab.processIncomingMessage(packet)
                }
            }
        }
    }
}