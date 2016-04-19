package kissybnts.fbmmessengerbot.model

import java.math.BigDecimal

/**
 * .
 */
data class FBMessengerBotWebhookEntryMessaging(val sender: Map<String, Long> = mapOf(), val  recipient: Map<String, Long> = mapOf(), val timestamp: Long = 0, val message: FBMessengerBotWebhookEntryMessagingMessage = FBMessengerBotWebhookEntryMessagingMessage()) {
}