package kissybnts.fbmmessengerbot.model

import java.math.BigDecimal

/**
 * .
 */
class FBMessengerBotWebhookEntryMessaging() {
    lateinit var sender: Map<String, BigDecimal>
    lateinit var  recipient: Map<String, BigDecimal>
    lateinit var timestamp: BigDecimal
    lateinit var message: FBMessengerBotWebhookEntryMessagingMessage

    constructor(sender: Map<String, BigDecimal>, recipient: Map<String, BigDecimal>, timestamp: BigDecimal, message: FBMessengerBotWebhookEntryMessagingMessage): this(){
        this.sender = sender
        this.recipient = recipient
        this.timestamp = timestamp
        this.message = message
    }
}