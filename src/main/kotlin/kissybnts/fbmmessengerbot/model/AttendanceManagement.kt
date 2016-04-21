package kissybnts.fbmmessengerbot.model

import kissybnts.fbmmessengerbot.domain.TimeCard
import kissybnts.fbmmessengerbot.dto.FBMessengerBotWebhookEntry
import kissybnts.fbmmessengerbot.repository.TimeCardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * .
 */
@Component
class AttendanceManagement @Autowired constructor(private val repository: TimeCardRepository) {

    fun start(entry: FBMessengerBotWebhookEntry): String {
        return try {
            repository.save(TimeCard(entry.id))
            "頑張ってこー"
        } catch(e: Exception) {
            errorProc(e)
        }
    }

    fun end(entry: FBMessengerBotWebhookEntry): String {
        return try {
            repository.otsukare(TimeCard.TimeCardPK(entry.id))
            "おつかれさまでした"
        } catch(e: Exception) {
            errorProc(e)
        }
    }

    fun revert(entry: FBMessengerBotWebhookEntry): String {
        try {
            val card: TimeCard? = repository.findOne(TimeCard.TimeCardPK(entry.id))
            card ?: return "それが嘘だ!!!"
            return if (card.endTime == null) {
                repository.delete(card)
                "出勤なんてなかった"
            } else {
                repository.save(card.apply { endTime = null })
                "あとちょっと頑張ろー"
            }
        } catch(e: Exception) {
            return errorProc(e)
        }
    }

    fun show(entry: FBMessengerBotWebhookEntry): String = try{ repository.findOne(TimeCard.TimeCardPK(entry.id)).toString() } catch(e: Exception) { errorProc(e) }

    fun showWeek(entry: FBMessengerBotWebhookEntry): String {
        try {
            val cards = repository.findAll()
            return if(cards.isEmpty()){
                "働いてないじゃん"
            } else {
                cards.map { "${toString()}¥r¥n" }.joinToString()
            }
        } catch(e: Exception) {
            return errorProc(e)
        }
    }

    fun deleteAll(entry: FBMessengerBotWebhookEntry): String{
        return try {
            repository.deleteAllBySenderId(entry.id)
            "仕事なんてなかった"
        } catch(e: Exception) {
            errorProc(e)
        }
    }

    private fun errorProc(e: Exception): String {
        println("ERROR ------------------")
        e.printStackTrace()
        println("END ERROR --------------")
        return "失敗"
    }
}