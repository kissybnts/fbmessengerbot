package kissybnts.fbmmessengerbot.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

/**
 * .
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class FBMessengerBotWebhookEntryMessagingMessage() {
    lateinit var mid: String
    lateinit var seq: BigDecimal
    lateinit var text: String
    constructor(mid: String, seq: BigDecimal, text: String): this(){
        this.mid = mid
        this.seq = seq
        this.text = text
    }
}