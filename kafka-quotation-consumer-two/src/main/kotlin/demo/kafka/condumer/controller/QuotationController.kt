package demo.kafka.condumer.controller

import demo.kafka.condumer.dto.QuotationDto
import demo.kafka.condumer.service.KafkaConsumer
import demo.kafka.condumer.service.QuotationListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink

@RestController
class QuotationController {
    @Autowired
    lateinit var kafkaConsumer: KafkaConsumer

    @PostMapping(value = ["/quotation"] , produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun getNewQuotation(): Flux<QuotationDto> {
        return createQuotationFlux()
    }

    fun createQuotationFlux(): Flux<QuotationDto>{
        return Flux.create { sink: FluxSink<QuotationDto> ->  kafkaConsumer.registerListener(object : QuotationListener {
            override fun onData(quotationDto: QuotationDto) {
                sink.next(quotationDto)
            }

            override fun onComplete() {
                sink.complete()
            }
        })}
    }
}