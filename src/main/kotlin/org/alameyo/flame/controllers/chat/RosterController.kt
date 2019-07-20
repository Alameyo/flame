package org.alameyo.flame.controllers.chat

import javafx.collections.ObservableSet
import org.alameyo.flame.controllers.JidOperations
import org.alameyo.flame.models.FlameContactEntry
import org.jivesoftware.smack.roster.Roster
import org.jivesoftware.smack.roster.RosterEntry
import org.jxmpp.jid.BareJid
import tornadofx.*

class RosterController(private val roster: Roster) : Controller(), JidOperations {

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

    fun removeContactFromRoster(flameContactEntry: FlameContactEntry) {
        roster.reloadAndWait()
        roster.removeEntry(entries().firstOrNull { it.jid == flameContactEntry.bareJid })
    }
}