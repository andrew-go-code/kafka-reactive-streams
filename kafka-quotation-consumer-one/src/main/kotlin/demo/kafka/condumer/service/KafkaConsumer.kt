package demo.kafka.condumer.service

import demo.kafka.condumer.dto.QuotationDto
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {
    private val logger = KotlinLogging.logger {}

    private var listener: QuotationListener? = null

    fun registerListener(quotationListener: QuotationListener) {
        this.listener = quotationListener
    }

    fun onEvent(quotationDto: QuotationDto) {
        listener?.onData(quotationDto)
    }

    fun onComplete() {
        listener?.onComplete()
    }

    @KafkaListener(topics = ["topic1"], containerFactory = "singleQuotationFactory")
    fun consume(quotationDto: QuotationDto, acknowledgment: Acknowledgment){
        logger.info { quotationDto }
        logger.info { "consumer 1" }
        logger.info { listener.toString() }
        logger.info { "====" }
        onEvent(quotationDto)
        acknowledgment.acknowledge()
    }

}