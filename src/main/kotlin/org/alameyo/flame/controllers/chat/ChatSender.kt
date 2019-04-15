package org.alameyo.flame.controllers.chat

import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.models.FlameContactEntry
import org.jivesoftware.smack.chat2.ChatManager
import tornadofx.find

class ChatMessageSender(jid: FlameContactEntry) {
    private val connection = find<FlameController>().connection

    private val chatManager = ChatManager.getInstanceFor(connection)
    private val chat = chatManager.chatWith(jid.bareJid.asEntityBareJidOrThrow())

    fun send(message: String) = chat.send(message)
}