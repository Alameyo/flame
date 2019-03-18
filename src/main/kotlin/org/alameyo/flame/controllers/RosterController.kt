package org.alameyo.flame.controllers

import org.jivesoftware.smack.roster.Roster
import org.jivesoftware.smack.roster.RosterEntry
import tornadofx.Controller

class RosterController(private val roster: Roster) : Controller() {

    fun entries(): MutableSet<RosterEntry> {
        roster.reloadAndWait()
        return roster.entries
    }
}