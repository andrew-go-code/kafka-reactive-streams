package demo.kafka.condumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaConsumerOneApp

fun main(args: Array<String>) {
    runApplication<KafkaConsumerOneApp>(*args)
}
