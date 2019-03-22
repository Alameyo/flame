package org.alameyo.flame.controllers

import org.alameyo.flame.models.ChatModel
import org.alameyo.flame.models.FlameRosterEntry
import org.alameyo.flame.views.chat.ChatAreaView
import tornadofx.*

class ChatAreaController: Controller() {

    private val setOfChats = mutableSetOf<ChatModel>()

    fun openChat(flameRosterEntry: FlameRosterEntry) {
        val chatToOpen = setOfChats.find { it.corespondent == flameRosterEntry.jid}?: throw NonExistentChatException()
        if(!chatToOpen.isOpen) {
            chatToOpen.isOpen = true
            find<ChatAreaView>().addChatTab(chatToOpen)
        }
    }

    fun addChat(flameRosterEntry: FlameRosterEntry) {
        val chatModel = ChatModel(flameRosterEntry.jid)
        setOfChats.add(chatModel)
    }

    fun addChatEntry() {

    }

    class NonExistentChatException: Exception()
}