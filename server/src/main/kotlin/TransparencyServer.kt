import controller.FeatureController
import controller.PackageController
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableAutoConfiguration
@Configuration
class TransparencyServer {
    @Bean
    fun featureController() = FeatureController()
    @Bean
    fun packageController() = PackageController()
}

fun main(args: Array<String>) {
    SpringApplication.run(TransparencyServer::class.java, *args)
}