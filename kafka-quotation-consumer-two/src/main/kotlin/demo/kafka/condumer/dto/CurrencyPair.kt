package demo.kafka.condumer.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CurrencyPair (@JsonProperty("currencyA") val currencyA: Currency, @JsonProperty("currencyB") val currencyB: Currency)