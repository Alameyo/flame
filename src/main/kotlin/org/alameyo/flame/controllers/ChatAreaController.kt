package org.alameyo.flame.controllers

import org.alameyo.flame.models.FlameRosterEntry
import org.alameyo.flame.views.home.chat.ChatAreaView
import org.alameyo.flame.views.home.chat.ChatTab
import tornadofx.Controller


class ChatAreaController : Controller() {

    private val setOfChats = mutableSetOf<ChatTab>()

    fun openChatWithRosterEntry(flameRosterEntry: FlameRosterEntry) {
        val chatToOpen = setOfChats.find { it.flameRosterEntry.jid == flameRosterEntry.jid } ?: throw NonExistentChatException()
        openChat(chatToOpen)
    }

    fun openChat(chatToOpen: ChatTab) {
        if (!chatToOpen.isOpen) {
            chatToOpen.isOpen = true
            find<ChatAreaView>().addChatTab(chatToOpen)
        }
    }

    fun addChat(flameRosterEntry: FlameRosterEntry) = setOfChats.add(ChatTab(flameRosterEntry))

    class NonExistentChatException : Exception("Cannot find chat tab which is meant to be opened")
}