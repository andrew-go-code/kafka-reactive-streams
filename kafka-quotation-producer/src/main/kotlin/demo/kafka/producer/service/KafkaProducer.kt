package demo.kafka.producer.service

import demo.kafka.producer.dto.QuotationDto
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.concurrent.ThreadLocalRandom

@Service
class KafkaProducer {
    private val logger = KotlinLogging.logger {}

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<Long, QuotationDto>

    @Autowired
    private lateinit var quotationGenerator: QuotationGenerator

    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    fun produce() {
        val newQuotation = newQuotation()
        logger.info { "generated new quotation : $newQuotation" }
        kafkaTemplate.send("topic1", newQuotation)
    }

    private fun newQuotation(): QuotationDto {
        return when (ThreadLocalRandom.current().nextInt(1, 3)){
            1 -> quotationGenerator.getEurUsdQuotation()
            2 -> quotationGenerator.getGbpUsdQuotation()
            3 -> quotationGenerator.getUsdChfQuotation()
            else -> throw RuntimeException("unexpected int value")
        }
    }
}
