package org.alameyo.flame.controllers.chat

import javafx.collections.ObservableSet
import org.jivesoftware.smack.roster.Roster
import org.jivesoftware.smack.roster.RosterEntry
import org.jxmpp.jid.BareJid
import tornadofx.*

class RosterController(private val roster: Roster) : Controller() {

    fun entries(): ObservableSet<RosterEntry> {
        roster.reloadAndWait()
        roster.subscriptionMode = Roster.SubscriptionMode.accept_all
        return roster.entries.observable()
    }

    fun addContactToRoster(bareJid: BareJid, name: String?) {
        roster.reloadAndWait()
        val formattedName = giveName(name, bareJid)
        roster.createEntry(bareJid, formattedName, null)
        roster.reloadAndWait()
        roster.sendSubscriptionRequest(bareJid)
    }

    private fun giveName(name: String?, bareJid: BareJid): String? {
        return when {
            !name.isNullOrEmpty() -> name
            else -> bareJid.localpartOrNull.toString()
        }
    }
}