package org.alameyo.flame.models

import org.jivesoftware.smack.roster.RosterEntry

class FlameRosterEntry(val name: String?, val jid: String) {
    constructor(rosterEntry: RosterEntry) : this(rosterEntry.name, rosterEntry.jid.toString())

    var chatModel: ChatModel? = null
}
