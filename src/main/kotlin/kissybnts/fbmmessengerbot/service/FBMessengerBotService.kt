package kissybnts.fbmmessengerbot.service

import com.squareup.moshi.Moshi
import kissybnts.fbmmessengerbot.dto.*
import kissybnts.fbmmessengerbot.model.MessageGenerator
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.IOException
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest

/**
 * .
 */
@Service
class FBMessengerBotService @Autowired constructor(val model: MessageGenerator) {
    private val VERIFY_TOKEN: String = "" //System.getenv("FBMESSENGERBOT_VERIFY_TOKEN")
    private val ACCESS_TOKEN: String = "" //System.getenv("FBMESSENGERBOT_ACCESS_TOKEN")
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
        try {
            val jb = StringBuilder().apply { request.reader.lines().forEach { append(it) } }.toString()
            val botResponse: FBmessengerBotWebhook = Moshi.Builder().build().adapter(FBmessengerBotWebhook::class.java).fromJson(jb)
            sendMessage(botResponse.entry.getOrNull(0)?: throw Exception("entry あらへん"))
        }catch(e: Exception){
            logger.error("ERROR --- ")
            e.printStackTrace()
        }
        return "ok"
    }

    private fun sendMessage(entry: FBMessengerBotWebhookEntry) {
        try {

            val builder: URIBuilder = URIBuilder(ENDPOINT).apply { setParameter("access_token", ACCESS_TOKEN) }
            val recipient: FBMessengerBotWebhookRecipient = FBMessengerBotWebhookRecipient(entry.messaging.getOrNull(0)?.sender?: throw  Exception())
            val post: HttpPost = HttpPost(builder.build()).apply { setHeader("Content-Type", "application/json; charset=UTF-8") }

            logger.info("receive message : ${entry.messaging.map { it.message.text }.joinToString()}")

            val returnMessage = model.generate(entry)

            logger.info("return message : $returnMessage")

            HttpClients.createDefault().use { sendOneRequest(it, post, recipient, returnMessage) }
        } catch(ie: IOException) {
            println("sendMessage : IOException")
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

    private fun sendOneRequest(client: CloseableHttpClient, post: HttpPost, recipient: FBMessengerBotWebhookRecipient, oneString: String) {
        recipient.message = mapOf("text" to oneString)

        val json: String = Moshi.Builder().build().adapter(FBMessengerBotWebhookRecipient::class.java).toJson(recipient)

        client.execute(post.apply { entity = StringEntity(json, StandardCharsets.UTF_8) })
    }
}