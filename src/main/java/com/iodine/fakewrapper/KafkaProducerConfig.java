package com.iodine.fakewrapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * @author victor
 * @date 10/17/19
 */
@Configuration
public class KafkaProducerConfig
{
	private static final String KAFKA_SERVER = "localhost:9092";
	private static final String KAFKA_TOPIC = "engine-events";
	private final static String GROUP_ID = "group1";
	private final static String CLIENT_ID = "documents1";

	@Bean
	public ProducerFactory<String, String> producerFactory()
	{
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	private Map<String, Object> producerConfigs()
	{
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, CLIENT_ID);

		return props;
	}

	@Bean
	public Serializer stringSerializer()
	{
		return new StringSerializer();
	}

	@Bean
	KafkaTemplate<String, String> producer(ProducerFactory<String, String> producerFactory)
	{
		KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory);
		kafkaTemplate.setDefaultTopic(KAFKA_TOPIC);
		return kafkaTemplate;
	}
}
