package kissybnts.fbmmessengerbot.domain

import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.*

/**
 * .
 */
@Entity
@Table(name = "time_card")
class TimeCard private constructor() {
    @EmbeddedId
    lateinit private var id: TimeCardPK
        private set

    @Column(name = "start_time", nullable = false)
    lateinit var startTime: String
        private set

    @Column(name = "end_time")
    var endTime: String? = null

    constructor(senderId: Long, date: String? = null, startTime: String = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME), endTime: String? = null): this(){
        this.id = date?.let { TimeCardPK(senderId, it) }?: TimeCardPK(senderId)
        this.startTime = startTime
        this.endTime = endTime
    }

    fun workDay(): String = this.id.date

    override fun toString(): String {
        return "${id.date} : $startTime 〜 ${endTime?: ""}"
    }

    @Embeddable
    class TimeCardPK(@Column(name = "sender_id") val senderId: Long = 0, @Column(name = "date") val date: String = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)): Serializable
}