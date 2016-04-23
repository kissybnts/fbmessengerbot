package kissybnts.fbmmessengerbot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class FbmessengerbotApplication {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(FbmessengerbotApplication::class.java, *args)
        }
    }
}

fun Exception.errorProc(): String {
    println("ERROR ----------------")
    this.printStackTrace()
    println("ERROR END ------------")
    return "失敗"
}