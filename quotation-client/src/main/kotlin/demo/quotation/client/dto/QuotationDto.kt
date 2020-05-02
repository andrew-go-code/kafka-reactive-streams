package demo.quotation.client.dto

data class QuotationDto(val id: String, val pair: CurrencyPair, val price: CurrencyPrice)
