package org.alameyo.flame.models

import org.jivesoftware.smack.roster.RosterEntry
import org.jivesoftware.smackx.bookmarks.BookmarkedConference
import org.jxmpp.jid.BareJid
import org.jxmpp.jid.parts.Resourcepart

class FlameContactEntry(val name: String?, val jid: String, val bareJid: BareJid, val nickname: Resourcepart? = null) {

    constructor(rosterEntry: RosterEntry) : this(rosterEntry.name, rosterEntry.jid.toString(), rosterEntry.jid)

    constructor(bookmarkedConference: BookmarkedConference) :
            this(bookmarkedConference.name, bookmarkedConference.jid.toString(), bookmarkedConference.jid.asBareJid(), bookmarkedConference.nickname)
}
