package kissybnts.fbmmessengerbot.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

/**
 * .
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class FBMessengerBotWebhookEntryMessaging() {
    @JsonProperty
    lateinit var sender: Map<String, BigDecimal>
    @JsonProperty
    lateinit var  recipient: Map<String, BigDecimal>
    @JsonProperty
    lateinit var timestamp: BigDecimal
    @JsonProperty
    lateinit var message: FBMessengerBotWebhookEntryMessagingMessage

    constructor(sender: Map<String, BigDecimal>, recipient: Map<String, BigDecimal>, timestamp: BigDecimal, message: FBMessengerBotWebhookEntryMessagingMessage): this(){
        this.sender = sender
        this.recipient = recipient
        this.timestamp = timestamp
        this.message = message
    }
}