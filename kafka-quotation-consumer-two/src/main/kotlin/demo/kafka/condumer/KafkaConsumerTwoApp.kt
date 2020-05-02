package demo.kafka.condumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaConsumerTwoApp

fun main(args: Array<String>) {
    runApplication<KafkaConsumerTwoApp>(*args)
}
