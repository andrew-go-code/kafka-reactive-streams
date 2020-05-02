package demo.quotation.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class QuotationClientApp

fun main(args: Array<String>) {
    runApplication<QuotationClientApp>(*args)
}
