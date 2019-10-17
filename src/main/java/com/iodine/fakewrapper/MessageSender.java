package com.iodine.fakewrapper;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

/**
 * @author victor
 * @date 10/17/19
 */
@Component
public class MessageSender
{
	@Autowired
	protected KafkaTemplate<String, String> producer;

	void sendMessage(Map<String, String> record)
	{
		try
		{
			// TODO: Verify this
			SendResult<String, String> result = producer.sendDefault(UUID.randomUUID().toString(), new JSONObject(record).toString()).get();
			RecordMetadata recordMetadata = result.getRecordMetadata();
			System.out.println(String.format("topic = %s, partition = %s, offset = %s, message = %s",
				recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), record));
		}
		catch (InterruptedException | ExecutionException e)
		{
			throw new RuntimeException(e);
		}
	}
}
