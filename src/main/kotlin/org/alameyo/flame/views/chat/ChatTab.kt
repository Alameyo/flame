package org.alameyo.flame.views.chat

import javafx.event.EventHandler
import javafx.scene.control.Tab
import javafx.scene.paint.Color
import javafx.scene.paint.Color.*
import org.alameyo.flame.models.ChatEntry
import org.alameyo.flame.models.FlameRosterEntry
import tornadofx.*
import java.lang.Exception

class ChatTab(val flameRosterEntry: FlameRosterEntry) : Tab(flameRosterEntry.name ?: flameRosterEntry.jid ?: throw ChatTabWithoutNameException()) {
    var isOpen = false
    val chatEntriesList = listOf<ChatEntry>()

    init {
        onClosed = EventHandler { isOpen = false }
        add(
            vbox {
                style {
                    backgroundColor += c("#27324E")
                    prefHeight = 500.px
                    maxHeight = 500.px
                    prefWidth = 600.px
                }
                val chatBox = scrollpane {
                    fitToWidthProperty().value = true
                    style {
                        baseColor = c("#27324E")
                        backgroundColor += TRANSPARENT
                        prefHeight = 500.px
                        maxHeight = 500.px
                        prefWidth = 600.px

                    }
                    vbox {

                    }
                }
                textfield {
                    promptText = "Send the message"
                    style {
                        prefWidth = 600.px
                    }
                    action {
                        chatBox.content += ChatEntry("ME", text)
                        text = ""
                        runLater(0.5.seconds) {
                            chatBox.vvalue = 1.0
                        }
                    }
                }
            })
    }

    private class ChatTabWithoutNameException : Exception("Created chat tab have no name")
}