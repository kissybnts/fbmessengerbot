package kissybnts.fbmmessengerbot.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

/**
 * Created by kishida on 2016/04/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
open class FBMessengerBotWebhookEntry() {
    @JsonProperty
    lateinit var id: BigDecimal
    @JsonProperty
    lateinit var time: BigDecimal
    @JsonProperty
    lateinit var messaging: MutableList<FBMessengerBotWebhookEntryMessaging>

    constructor(id: BigDecimal, time: BigDecimal, messaging: MutableList<FBMessengerBotWebhookEntryMessaging>): this(){
        this.id = id
        this.time = time
        this.messaging = messaging
    }
}