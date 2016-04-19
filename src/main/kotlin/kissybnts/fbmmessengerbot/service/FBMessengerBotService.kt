package kissybnts.fbmmessengerbot.service

import com.fasterxml.jackson.databind.ObjectMapper
import kissybnts.fbmmessengerbot.model.FBMessengerBotWebhookEntryMessaging
import kissybnts.fbmmessengerbot.model.FBMessengerBotWebhookEntryMessagingMessage
import kissybnts.fbmmessengerbot.model.FBMessengerBotWebhookRecipient
import kissybnts.fbmmessengerbot.model.FBmessengerBotWebhook
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.log4j.Logger
import org.apache.log4j.spi.LoggerFactory
import org.springframework.stereotype.Service
import java.io.IOException
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest

/**
 * .
 */
@Service
class FBMessengerBotService {
    private val VERIFY_TOKEN: String = System.getenv("FBMESSENGERBOT_VERIFY_TOKEN")
    private val ACCESS_TOKEN: String = System.getenv("FBMESSENGERBOT_ACCESS_TOKEN")
    private val ENDPOINT: String = "https://graph.facebook.com/v2.6/me/messages"
    private val logger: Logger = Logger.getLogger(FBMessengerBotService::class.java)

    fun verify(request: HttpServletRequest): String {
        try {
            val paramMap = request.parameterMap
            if(paramMap["hub.mode"]!![0].equals("subscribe") && paramMap["hub.verify_token"]!![0].equals(VERIFY_TOKEN)){
                logger.info(paramMap["hub.challenge"]!!.joinToString())
                return paramMap["hub.challenge"]!!.joinToString()
            }
        } catch(e: Exception){
            e.printStackTrace()
        }
        return "failed"
    }

    fun sentToMessenger(request: HttpServletRequest): String {
        logger.info("first : ${request.reader.lines().toArray()}")
        val sb = StringBuilder()
        request.reader.lines().toArray().forEach { sb.append(it) }
        val jb = sb.toString()
        logger.info("sentToMessenger() request : $jb")
        val botResponse = ObjectMapper().readValue(jb, FBmessengerBotWebhook::class.java)
        botResponse.entry.forEach { e -> e.messaging.forEach { m -> sendMessage(m) } }
        return "ok"
    }

    fun sendMessage(messaging: FBMessengerBotWebhookEntryMessaging) {
        try {
            val message: FBMessengerBotWebhookEntryMessagingMessage = messaging.message

            val builder: URIBuilder = URIBuilder(ENDPOINT).apply { setParameter("access_token", ACCESS_TOKEN) }
            val recipient: FBMessengerBotWebhookRecipient = FBMessengerBotWebhookRecipient(messaging.sender)
            val post: HttpPost = HttpPost(builder.build()).apply { setHeader("Content-Type", "application/json; charset=UTF-8") }

            //val text: List<String> = message.text.split("")
            logger.info("sendMessage() text: ${message.text}")
            HttpClients.createDefault().use { sendOneRequest(it, post, recipient, message.text) }
        } catch(ie: IOException) {
            println("sendMessage : IOException")
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

    fun sendOneRequest(client: CloseableHttpClient, post: HttpPost, recipient: FBMessengerBotWebhookRecipient, oneString: String) {
        recipient.message = mapOf("text" to oneString)

        val json: String = ObjectMapper().writeValueAsString(recipient)

        logger.info("sendOneRequest() message: $json")
        client.execute(post.apply { entity = StringEntity(json, StandardCharsets.UTF_8) })
    }
}