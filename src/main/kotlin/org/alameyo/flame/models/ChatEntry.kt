package org.alameyo.flame.models

import javafx.geometry.Pos.BASELINE_LEFT
import tornadofx.*
import java.time.LocalTime.now
import java.time.format.DateTimeFormatter.ofPattern


/**
 * This class format message to form displayable in chatroom.
 */
class ChatEntry(from: String, message: String) : View() {

    private val entry: String
    private val time = now()
    private val formatter = ofPattern("HH:mm:ss")
    private val timeString = formatter.format(time)

    init {
        entry = "$timeString | $from:$message\n"
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
