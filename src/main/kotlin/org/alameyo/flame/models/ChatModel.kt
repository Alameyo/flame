package org.alameyo.flame.models

class ChatModel(val corespondent: String) {

    var isOpen = false
    val chatEntriesList = listOf<ChatEntry>()
}