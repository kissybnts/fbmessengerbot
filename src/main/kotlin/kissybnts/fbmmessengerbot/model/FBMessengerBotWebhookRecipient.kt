package kissybnts.fbmmessengerbot.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

/**
 * Created by kishida on 2016/04/17.
 */
data class FBMessengerBotWebhookRecipient(var recipient: Map<String, BigDecimal> = mapOf(),
                                          var message: Map<String, String> = mapOf()) {
}