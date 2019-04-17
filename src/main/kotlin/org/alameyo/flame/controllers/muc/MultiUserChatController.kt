package org.alameyo.flame.controllers.muc

import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.views.home.muc.MultiUserChatTab
import org.jivesoftware.smackx.muc.MultiUserChatManager
import tornadofx.find

class MultiUserChatController(chatTab: MultiUserChatTab) {

    private val connection = find<FlameController>().connection
    private val multiUserChatManager = MultiUserChatManager.getInstanceFor(connection)
    private val chat = multiUserChatManager.getMultiUserChat(chatTab.flameContactEntry.bareJid.asEntityBareJidOrThrow())
    private val multiUserChatSender = MultiUserChatSender(chat)

    init {
        MultiUserChatStanzaListener(chatTab, chat)
        chat.join(chatTab.flameContactEntry.nickname)
    }

    fun send(message: String) {
        multiUserChatSender.sendMessage(message)
    }
}