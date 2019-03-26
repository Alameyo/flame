package org.alameyo.flame.models

import org.alameyo.flame.views.home.chat.ChatTab
import org.jivesoftware.smack.roster.RosterEntry

class FlameRosterEntry(val rosterEntry: RosterEntry) {
    val name: String? = rosterEntry.name
    val jid = rosterEntry.jid.toString()

    var chatTab: ChatTab? = null
}
