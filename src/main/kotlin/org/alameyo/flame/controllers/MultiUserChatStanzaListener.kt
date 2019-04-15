package org.alameyo.flame.controllers

import org.alameyo.flame.views.home.muc.MultiUserChatTab
import org.jivesoftware.smack.MessageListener
import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smackx.muc.MultiUserChatManager
import tornadofx.find

class MultiUserChatStanzaListener(private val chatTab: MultiUserChatTab) : MessageListener {

    private val connection = find<FlameController>().connection
    private val multiUserChatManager = MultiUserChatManager.getInstanceFor(connection)

    init {
        val chat = multiUserChatManager.getMultiUserChat(chatTab.flameContactEntry.bareJid.asEntityBareJidOrThrow())

        chat.join(chatTab.flameContactEntry.nickname)
        chat.addMessageListener(this)
    }

    override fun processMessage(message: Message?) {
        if (message?.body != null) {
            chatTab.processIncomingMessage(message)
        }
    }
}