package kissybnts.fbmmessengerbot.model

import java.math.BigDecimal

/**
 * .
 */
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