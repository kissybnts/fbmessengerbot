package kissybnts.fbmmessengerbot.model

import java.math.BigDecimal

/**
 * Created by kishida on 2016/04/17.
 */
data class FBMessengerBotWebhookEntryMessagingMessage(val mid: String, val seq: BigDecimal, val text: String) {
}