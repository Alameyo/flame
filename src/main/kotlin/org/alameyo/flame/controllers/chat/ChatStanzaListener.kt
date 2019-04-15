package org.alameyo.flame.controllers.chat

import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.views.home.chat.ChatTab
import org.jivesoftware.smack.StanzaListener
import org.jivesoftware.smack.filter.AndFilter
import org.jivesoftware.smack.filter.FromMatchesFilter
import org.jivesoftware.smack.filter.StanzaFilter
import org.jivesoftware.smack.filter.StanzaTypeFilter
import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.packet.Stanza
import tornadofx.find

class ChatStanzaListener(private val chatTab: ChatTab) : StanzaListener {

    private val connection = find<FlameController>().connection
    private val filter: StanzaFilter = AndFilter(StanzaTypeFilter.MESSAGE, FromMatchesFilter.create(chatTab.flameContactEntry.bareJid))

    init {
        connection.addAsyncStanzaListener(this, filter)
    }

    override fun processStanza(packet: Stanza?) {
        when (packet) {
            is Message -> if (packet.body != null) {
                chatTab.processIncomingMessage(packet)
            }
        }
    }
}