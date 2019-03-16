package org.alameyo.flame.models

import org.jivesoftware.smack.packet.Message
import java.time.format.DateTimeFormatter
import java.time.LocalTime


/**
 * This class format message to form displayable in chatroom.
 */
class ChatEntry(from: String, message: String) {
    val entry: String
    val time = LocalTime.now()
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    val timeString = formatter.format(time)

    init {
        entry = "$timeString | $from:$message\n"
    }
}
