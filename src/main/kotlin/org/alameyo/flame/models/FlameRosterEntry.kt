package org.alameyo.flame.models

import org.alameyo.flame.views.chat.ChatTab
import org.jivesoftware.smack.roster.RosterEntry

class FlameRosterEntry(val name: String?, val jid: String) {
    constructor(rosterEntry: RosterEntry) : this(rosterEntry.name, rosterEntry.jid.toString())

    var chatTab: ChatTab? = null
}
