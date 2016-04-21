package kissybnts.fbmmessengerbot.repository

import kissybnts.fbmmessengerbot.domain.TimeCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * .
 */
@Repository
interface TimeCardRepository : JpaRepository<TimeCard, TimeCard.TimeCardPK> {
    @Modifying
    @Query("update TimeCard t set t.endTime = :end where t.id = :id")
    fun otsukare(@Param("id") id: TimeCard.TimeCardPK, @Param("end") end: String = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME)): Int

    @Modifying
    @Query("delete TimeCard t where t.id.senderId = :sender_id")
    fun deleteAllBySenderId(@Param("sender_id") senderId: Long): Int
}