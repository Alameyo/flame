package org.alameyo.flame.controllers.muc

import org.alameyo.flame.controllers.FlameController
import org.jivesoftware.smackx.bookmarks.BookmarkManager
import org.jivesoftware.smackx.bookmarks.BookmarkedConference
import tornadofx.Controller

class BookmarksController : Controller() {

    private val flameController: FlameController by inject()
    private val bookmarkManager = BookmarkManager.getBookmarkManager(flameController.connection)

    fun bookmarks() = bookmarkManager.bookmarkedConferences as List<BookmarkedConference>
}