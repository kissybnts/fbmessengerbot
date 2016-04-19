package kissybnts.fbmmessengerbot.model

import java.math.BigDecimal

/**
 * Created by kishida on 2016/04/17.
 */
data class FBMessengerBotWebhookRecipient(val recipient: Map<String, BigDecimal> = mapOf(), var message: Map<String, String> = mapOf()) {
}