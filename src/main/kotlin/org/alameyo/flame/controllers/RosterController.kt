package org.alameyo.flame.controllers

import org.jivesoftware.smack.roster.Roster
import tornadofx.Controller

class RosterController(val roster: Roster): Controller() {

    fun entries() = roster.entries
}