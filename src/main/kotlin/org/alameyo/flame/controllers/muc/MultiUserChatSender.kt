package org.alameyo.flame.controllers.muc

import org.jivesoftware.smackx.muc.MultiUserChat

class MultiUserChatSender(val chat: MultiUserChat) {

    fun sendMessage(message: String) = chat.sendMessage(message)
}