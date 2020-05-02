package demo.quotation.client.service

import demo.quotation.client.dto.QuotationDto
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class QuotationClient {
    private val logger = KotlinLogging.logger {}

    @Value("\${quotation.server}")
    private lateinit var server: String

    @Value("\${quotation.uri}")
    private lateinit var uri: String

    @Autowired
    private lateinit var webClientBuilder: WebClient.Builder

    @EventListener(ApplicationStartedEvent::class)
    fun start() {
        val webClient = webClientBuilder
                .baseUrl(server)
                .build()

        val fluxQuotationDto  = webClient
                .post()
                .uri(uri)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(QuotationDto::class.java)


        fluxQuotationDto
                .doOnNext { logger.info { "=>$it" } }
//                .then()
                .subscribe()
    }
}