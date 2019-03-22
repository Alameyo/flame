package org.alameyo.flame.models

import tornadofx.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter


/**
 * This class format message to form displayable in chatroom.
 */
class ChatEntry(from: String, message: String): View() {

    private val entry: String
    private val time = LocalTime.now()
    private val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    private val timeString = formatter.format(time)

    init {
        entry = "$timeString | $from:$message\n"
    }

    override val root = hbox{
        button(entry) {
            style{
                minWidth = 80.px
                minHeight = 80.px
                maxWidth = 80.px
                maxHeight = 80.px
            }
        }
    }
}
