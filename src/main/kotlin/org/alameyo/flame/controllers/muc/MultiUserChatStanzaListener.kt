package org.alameyo.flame.controllers.muc

import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.views.home.muc.MultiUserChatTab
import org.jivesoftware.smack.MessageListener
import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smackx.muc.MultiUserChat
import org.jivesoftware.smackx.muc.MultiUserChatManager
import tornadofx.find

class MultiUserChatStanzaListener(private val chatTab: MultiUserChatTab, chat: MultiUserChat) : MessageListener {

    init {
        chat.join(chatTab.flameContactEntry.nickname)
        chat.addMessageListener(this)
    }

    override fun processMessage(message: Message?) {
        if (message?.body != null) {
            chatTab.processIncomingMessage(message)
        }
    }
}