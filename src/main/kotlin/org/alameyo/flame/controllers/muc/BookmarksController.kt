package org.alameyo.flame.controllers.muc

import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.controllers.JidOperations
import org.jivesoftware.smackx.bookmarks.BookmarkManager
import org.jxmpp.jid.BareJid
import org.jxmpp.jid.impl.JidCreate.entityBareFrom
import tornadofx.Controller
import tornadofx.observable

class BookmarksController : Controller(), JidOperations {

    private val flameController: FlameController by inject()
    private val bookmarkManager = BookmarkManager.getBookmarkManager(flameController.connection)

    fun bookmarks() = bookmarkManager.bookmarkedConferences.toSet().observable()

    fun addBookmark(bareJid: BareJid, name: String?) {
        val formattedName = giveName(name, bareJid)
        bookmarkManager.addBookmarkedConference(formattedName, entityBareFrom(bareJid), true, flameController.connection.user.resourceOrEmpty, null)
    }

}