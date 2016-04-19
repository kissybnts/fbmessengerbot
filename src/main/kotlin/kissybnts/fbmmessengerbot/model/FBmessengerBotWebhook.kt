package kissybnts.fbmmessengerbot.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * .
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class FBmessengerBotWebhook() {
    @JsonProperty
    lateinit var `object`: String
    @JsonProperty
    lateinit var entry:List<FBMessengerBotWebhookEntry>

    constructor(`object`: String, entry:List<FBMessengerBotWebhookEntry>): this(){
        this.`object` = `object`
        this.entry = entry
    }
}