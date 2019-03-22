package org.alameyo.flame.views.chat

import javafx.event.EventHandler
import javafx.scene.control.Tab
import org.alameyo.flame.models.ChatEntry
import org.alameyo.flame.models.FlameRosterEntry
import tornadofx.*

class ChatTab(val flameRosterEntry: FlameRosterEntry): Tab(flameRosterEntry.name?:flameRosterEntry.jid) {
    var isOpen = false
    val chatEntriesList = listOf<ChatEntry>()

    init {
        onClosed = EventHandler { isOpen = false }
    }

}