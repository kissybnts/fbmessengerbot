package kissybnts.fbmmessengerbot.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

/**
 * Created by kishida on 2016/04/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class FBMessengerBotWebhookEntry() {
    lateinit var id: BigDecimal
    lateinit var time: BigDecimal
    lateinit var messaging: List<FBMessengerBotWebhookEntryMessaging>

    constructor(id: BigDecimal, time: BigDecimal, messaging: List<FBMessengerBotWebhookEntryMessaging>): this(){
        this.id = id
        this.time = time
        this.messaging = messaging
    }
}