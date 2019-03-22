package org.alameyo.flame.controllers

import org.alameyo.flame.views.home.chat.ChatTab
import org.alameyo.flame.models.FlameRosterEntry
import org.alameyo.flame.views.home.chat.ChatAreaView
import tornadofx.*

class ChatAreaController: Controller() {

    private val setOfChats = mutableSetOf<ChatTab>()

    fun openChat(flameRosterEntry: FlameRosterEntry) {
        val chatToOpen = setOfChats.find { it.flameRosterEntry.jid == flameRosterEntry.jid}?: throw NonExistentChatException()
        if(!chatToOpen.isOpen) {
            chatToOpen.isOpen = true
            find<ChatAreaView>().addChatTab(chatToOpen)
        }
    }

    fun addChat(flameRosterEntry: FlameRosterEntry) = setOfChats.add(ChatTab(flameRosterEntry))

    class NonExistentChatException: Exception("Cannot find chat tab which is meant to be opened")
}