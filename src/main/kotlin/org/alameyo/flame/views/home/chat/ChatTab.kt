package org.alameyo.flame.views.home.chat

import javafx.event.EventHandler
import javafx.scene.control.ScrollPane
import javafx.scene.control.Tab
import javafx.scene.control.TextField
import org.alameyo.flame.controllers.chat.ChatMessageSender
import org.alameyo.flame.models.FlameContactEntry
import org.jivesoftware.smack.packet.Message

abstract class ChatTab(val flameContactEntry: FlameContactEntry) : Tab(flameContactEntry.name ?: flameContactEntry.jid ?: throw ChatTabWithoutNameException()) {

    var isOpen = false
    protected val chatEntriesList = mutableListOf<ChatEntryView>()
    protected lateinit var chatBox: ScrollPane
    protected lateinit var promptTextField: TextField

    init {
        onClosed = EventHandler { isOpen = false }
    }

    abstract fun processIncomingMessage(stanza: Message)
}

private class ChatTabWithoutNameException : Exception("Created chat tab have no name")