package org.alameyo.flame.views.home.chat

import javafx.geometry.Pos.BASELINE_LEFT
import tornadofx.*
import java.time.LocalTime.now
import java.time.format.DateTimeFormatter.ofPattern


/**
 * This class format message to form displayable in chatroom.
 */
class ChatEntryView(from: String, message: String) : View() {

    private val entry: String
    private val time = now()
    private val formatter = ofPattern("HH:mm:ss")
    private val timeString = formatter.format(time)
    private val fromMe = if (from == "ME") {
        "ME"
    } else {
        ""
    }

    init {
        entry = "$timeString | $fromMe:$message\n"
    }

    override val root = hbox {
        button(entry) {
            wrapTextProperty().value = true
            style {
                alignment = BASELINE_LEFT
                prefWidth = Int.MAX_VALUE.px
                maxWidth = Int.MAX_VALUE.px
            }
        }
    }
}
