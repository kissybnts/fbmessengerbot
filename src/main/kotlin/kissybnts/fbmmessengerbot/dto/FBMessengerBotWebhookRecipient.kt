package kissybnts.fbmmessengerbot.dto

/**
 * Created by kishida on 2016/04/17.
 */
data class FBMessengerBotWebhookRecipient(var recipient: Map<String, Long> = mapOf(),
                                          var message: Map<String, String> = mapOf()) {
}