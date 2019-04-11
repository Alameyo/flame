package org.alameyo.flame.controllers.chat

import org.alameyo.flame.models.FlameRosterEntry
import org.alameyo.flame.views.home.chat.ChatAreaView
import org.alameyo.flame.views.home.chat.DirectChatTab
import tornadofx.Controller


class ChatAreaController : Controller() {

    private val setOfChats = mutableSetOf<DirectChatTab>()

    fun openChatWithRosterEntry(flameRosterEntry: FlameRosterEntry) {
        val chatToOpen = setOfChats.find { it.flameRosterEntry.jid == flameRosterEntry.jid } ?: throw NonExistentChatException()
        openChat(chatToOpen)
    }

    fun openChat(directChatToOpen: DirectChatTab) {
        if (!directChatToOpen.isOpen) {
            directChatToOpen.isOpen = true
            find<ChatAreaView>().addChatTab(directChatToOpen)
        }
    }

    fun addChat(flameRosterEntry: FlameRosterEntry) = setOfChats.add(DirectChatTab(flameRosterEntry))

    class NonExistentChatException : Exception("Cannot find chat tab which is meant to be opened")
}