package demo.kafka.producer.dto

import java.util.*

data class QuotationDto(val pair: CurrencyPair, val price: CurrencyPrice, val id: String = generateId()) {
    companion object {
        private fun generateId(): String {
            return UUID.randomUUID().toString()
        }
    }
}
