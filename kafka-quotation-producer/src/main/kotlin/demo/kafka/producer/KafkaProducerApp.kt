package demo.kafka.producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class KafkaProducerApp

fun main(args: Array<String>) {
    runApplication<KafkaProducerApp>(*args)
}
