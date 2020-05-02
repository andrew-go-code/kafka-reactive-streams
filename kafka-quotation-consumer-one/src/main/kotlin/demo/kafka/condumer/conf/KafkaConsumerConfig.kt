package demo.kafka.condumer.conf

import demo.kafka.condumer.dto.QuotationDto
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter
import org.springframework.kafka.support.converter.StringJsonMessageConverter

@Configuration
class KafkaConsumerConfig {

    @Value("\${kafka.server}")
    private lateinit var kafkaServer: String

    @Value("\${kafka.group.id}")
    private lateinit var kafkaGroupId: String

    @Bean
    fun singleQuotationFactory(): KafkaListenerContainerFactory<*> {
        val factory: ConcurrentKafkaListenerContainerFactory<Long, QuotationDto> =
                ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory()
        factory.isBatchListener = false
        factory.setMessageConverter(singleConverter())
        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
        return factory
    }

    @Bean
    fun batchQuotationFactory(): KafkaListenerContainerFactory<*> {
        val factory: ConcurrentKafkaListenerContainerFactory<Long, QuotationDto> =
                ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory()
        factory.isBatchListener = true
        factory.setMessageConverter(batchConverter())
        return factory
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<Long, QuotationDto> {
        return DefaultKafkaConsumerFactory(consumerConfig())
    }

    @Bean
    fun consumerConfig(): Map<String, Any> {
        return mapOf(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaServer,
                ConsumerConfig.GROUP_ID_CONFIG to kafkaGroupId,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to LongDeserializer::class.java.name,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java.name,
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to false
        )
    }

    @Bean
    fun singleConverter(): StringJsonMessageConverter {
        return StringJsonMessageConverter()
    }

    @Bean
    fun batchConverter(): BatchMessagingMessageConverter {
        return BatchMessagingMessageConverter(singleConverter())
    }

}