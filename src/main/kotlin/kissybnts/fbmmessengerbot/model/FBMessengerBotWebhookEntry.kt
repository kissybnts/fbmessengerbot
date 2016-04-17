package kissybnts.fbmmessengerbot.model

import java.math.BigDecimal

/**
 * Created by kishida on 2016/04/17.
 */
data class FBMessengerBotWebhookEntry(val id: BigDecimal, val time: BigDecimal, val messaging: List<FBMessengerBotWebhookEntryMessaging>) {
}