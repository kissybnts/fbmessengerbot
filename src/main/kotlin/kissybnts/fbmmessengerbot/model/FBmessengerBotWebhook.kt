package kissybnts.fbmmessengerbot.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * .
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class FBmessengerBotWebhook() {
    lateinit var `object`: String
    lateinit var entry:List<FBMessengerBotWebhookEntry>

    constructor(`object`: String, entry:List<FBMessengerBotWebhookEntry>): this(){
        this.`object` = `object`
        this.entry = entry
    }
}