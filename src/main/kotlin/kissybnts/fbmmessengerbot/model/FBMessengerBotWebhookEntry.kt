package kissybnts.fbmmessengerbot.model

import java.math.BigDecimal

/**
 * Created by kishida on 2016/04/17.
 */
open class FBMessengerBotWebhookEntry(val id: BigDecimal = BigDecimal(0), val time: BigDecimal = BigDecimal(0), val messaging: MutableList<FBMessengerBotWebhookEntryMessaging> = mutableListOf()) {
}