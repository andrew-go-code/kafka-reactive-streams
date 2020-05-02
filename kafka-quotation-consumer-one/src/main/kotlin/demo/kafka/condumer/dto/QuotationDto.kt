package demo.kafka.condumer.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class QuotationDto(val id: String, @JsonProperty( "pair") val pair: CurrencyPair, @JsonProperty("price") val price: CurrencyPrice)
