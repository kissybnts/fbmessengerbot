package kissybnts.fbmmessengerbot.dto

/**
 * Created by kishida on 2016/04/17.
 */
open class FBMessengerBotWebhookEntry(val id: Long = 0, val time: Long = 0, val messaging: MutableList<FBMessengerBotWebhookEntryMessaging> = mutableListOf()) {
}