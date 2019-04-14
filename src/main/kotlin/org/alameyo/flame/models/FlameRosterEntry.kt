package org.alameyo.flame.models

import org.jivesoftware.smack.roster.RosterEntry
import org.jivesoftware.smackx.bookmarks.BookmarkedConference
import org.jxmpp.jid.BareJid

data class FlameRosterEntry private constructor(val name: String?, val jid: String, val bareJid: BareJid) {

    constructor(rosterEntry: RosterEntry) : this(rosterEntry.name, rosterEntry.jid.toString(), rosterEntry.jid)

    constructor(bookmarkedConference: BookmarkedConference) : this(bookmarkedConference.name, bookmarkedConference.jid.toString(), bookmarkedConference.jid.asBareJid())
}
