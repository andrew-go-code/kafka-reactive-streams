package demo.kafka.condumer.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CurrencyPrice (@JsonProperty("bid") val bid: Double, @JsonProperty("ask") val ask: Double)