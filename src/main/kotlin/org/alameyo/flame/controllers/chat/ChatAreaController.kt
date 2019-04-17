package org.alameyo.flame.controllers.chat

import org.alameyo.flame.models.FlameContactEntry
import org.alameyo.flame.views.home.chat.ChatAreaView
import org.alameyo.flame.views.home.chat.ChatTab
import org.alameyo.flame.views.home.chat.DirectChatTab
import org.alameyo.flame.views.home.muc.MultiUserChatTab
import tornadofx.Controller

class ChatAreaController : Controller() {

    private val setOfChats = mutableSetOf<ChatTab>()

    fun openChatWithRosterEntry(flameContactEntry: FlameContactEntry) {
        val chatToOpen = setOfChats.find { it.flameContactEntry.jid == flameContactEntry.jid } ?: throw NonExistentChatException()
        openChat(chatToOpen)
    }

    private fun openChat(chatToOpen: ChatTab) {
        if (!chatToOpen.isOpen) {
            chatToOpen.isOpen = true
            find<ChatAreaView>().addChatTab(chatToOpen)
        }
    }

    fun addChat(flameContactEntry: FlameContactEntry) = setOfChats.add(DirectChatTab(flameContactEntry))

    fun addMuc(flameContactEntry: FlameContactEntry) = setOfChats.add(MultiUserChatTab(flameContactEntry))

    class NonExistentChatException : Exception("Cannot find chat tab which is meant to be opened")
}
