package kissybnts.fbmmessengerbot.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * .
 */
@JsonIgnoreProperties(ignoreUnknown = true)
open class FBmessengerBotWebhook() {
    @JsonProperty
    lateinit var `object`: String
    @JsonProperty
    lateinit var entry: MutableList<FBMessengerBotWebhookEntry>

    constructor(`object`: String, entry: MutableList<FBMessengerBotWebhookEntry>): this(){
        this.`object` = `object`
        this.entry = entry
    }
}