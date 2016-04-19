package kissybnts.fbmmessengerbot.controller

import kissybnts.fbmmessengerbot.service.FBMessengerBotService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
 * Created by kishida on 2016/04/17.
 */
@RestController
@RequestMapping("/fbmessengerbot")
class FBMessengerBotController @Autowired constructor (private val botService: FBMessengerBotService) {
    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun verify(request: HttpServletRequest): String = botService.verify(request)

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun message(request: HttpServletRequest): String {
        println("get request")
        return botService.sentToMessenger(request)
    }
}