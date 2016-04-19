package kissybnts.fbmmessengerbot.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

/**
 * .
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class FBMessengerBotWebhookEntryMessagingMessage() {
    @JsonProperty
    lateinit var mid: String
    @JsonProperty
    lateinit var seq: BigDecimal
    @JsonProperty
    lateinit var text: String

    constructor(mid: String, seq: BigDecimal, text: String): this(){
        this.mid = mid
        this.seq = seq
        this.text = text
    }
}