package org.alameyo.flame.controllers

import org.jxmpp.jid.BareJid

interface JidOperations {
    fun giveName(name: String?, bareJid: BareJid): String? {
        return when {
            name.isNullOrEmpty() -> name
            else -> bareJid.localpartOrNull.toString()
        }
    }
}