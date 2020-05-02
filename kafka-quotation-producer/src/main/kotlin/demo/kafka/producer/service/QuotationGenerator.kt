package demo.kafka.producer.service

import demo.kafka.producer.dto.Currency
import demo.kafka.producer.dto.CurrencyPair
import demo.kafka.producer.dto.CurrencyPrice
import demo.kafka.producer.dto.QuotationDto
import org.springframework.stereotype.Service
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.atomic.AtomicReference

@Service
class QuotationGenerator {
    companion object {
        private val currentBidEurUsd = AtomicReference(1.3580)
        private val currentBidUsdChf = AtomicReference(0.9787)
        private val currentBidGbpUsd = AtomicReference(1.5825)
    }

    fun getEurUsdQuotation(): QuotationDto {
        val price = generateNextPrice(currentBidEurUsd.get())
        return QuotationDto(CurrencyPair(Currency.EUR, Currency.USD), price)
    }

    fun getUsdChfQuotation(): QuotationDto {
        val price = generateNextPrice(currentBidUsdChf.get())
        return QuotationDto(CurrencyPair(Currency.USD, Currency.CHF), price)
    }

    fun getGbpUsdQuotation(): QuotationDto {
        val price = generateNextPrice(currentBidGbpUsd.get())
        return QuotationDto(CurrencyPair(Currency.GBP, Currency.USD), price)
    }

    private fun generateNextPrice(currentVal: Double): CurrencyPrice {
        val randomDouble = ThreadLocalRandom.current().nextDouble(0.0001, 0.9999)
        val randomBoolean = ThreadLocalRandom.current().nextBoolean()

        val bid = if (randomBoolean) currentVal.plus(randomDouble) else currentVal.minus(randomDouble)
        val ask = bid + 0.0002;

        return CurrencyPrice(bid, ask)
    }
}