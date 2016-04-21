package kissybnts.fbmmessengerbot.model

import kissybnts.fbmmessengerbot.dto.FBMessengerBotWebhookEntry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * .
 */
@Component
class MessageGenerator @Autowired constructor(private val manager: AttendanceManagement) {
    fun generate(entry: FBMessengerBotWebhookEntry): String {
        return getMessage(entry)
    }

    private fun getMessage(entry: FBMessengerBotWebhookEntry): String {
        val receiveMessage = entry.messaging.map { it.message.text }.joinToString()

        return when(receiveMessage) {
            "始まった" -> manager.start(entry)
            "終わったよ" -> manager.end(entry)
            "さっきの嘘" -> manager.revert(entry)
            "見せて" -> manager.show(entry)
            "今週の" -> manager.showWeek(entry)
            "バルス" -> manager.deleteAll(entry)
            else -> receiveMessage
        }
    }


}