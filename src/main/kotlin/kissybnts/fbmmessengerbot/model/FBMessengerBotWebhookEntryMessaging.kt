package kissybnts.fbmmessengerbot.model

import java.math.BigDecimal

/**
 * Created by kishida on 2016/04/17.
 */
data class FBMessengerBotWebhookEntryMessaging(val sender: Map<String, BigDecimal>,
                                               val recipient: Map<String, BigDecimal>,
                                               val timestamp: BigDecimal,
                                               val message: FBMessengerBotWebhookEntryMessagingMessage) {
}