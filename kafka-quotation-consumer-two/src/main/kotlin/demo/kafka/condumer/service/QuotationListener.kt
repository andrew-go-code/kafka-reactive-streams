package demo.kafka.condumer.service

import demo.kafka.condumer.dto.QuotationDto

interface QuotationListener {
    fun onData(quotationDto: QuotationDto)
    fun onComplete()
}