package kissybnts.fbmmessengerbot.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.URI
import javax.sql.DataSource

/**
 * .
 */
@Configuration
open class AppConfig() {
    @Autowired
    lateinit var properties: DataSourceProperties

    @Bean(destroyMethod = "close")
    open fun dataSource(): DataSource {
        val databaseUrl: String? = System.getenv("DATABASE_URL")
        databaseUrl?: throw Exception("DATABASE_URL is nothing")
        val uri = URI(databaseUrl)
        val url = "jdbc:postgresql://${uri.host}${uri.path}:${uri.port}${uri.path}"
        val userName = uri.userInfo.split(":")[0]
        val password = uri.userInfo.split(":")[1]

        val dataSource = DataSourceBuilder.create(properties.classLoader).url(url).username(userName).password(password).build()
        return dataSource
    }
}