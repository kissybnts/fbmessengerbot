package kissybnts.fbmmessengerbot.model

import java.math.BigDecimal

/**
 * .
 */
data class FBMessengerBotWebhookEntryMessaging(val sender: Map<String, BigDecimal> = mapOf(), val  recipient: Map<String, BigDecimal> = mapOf(), val timestamp: BigDecimal = BigDecimal(0), val message: FBMessengerBotWebhookEntryMessagingMessage = FBMessengerBotWebhookEntryMessagingMessage()) {
}