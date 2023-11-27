package com.Attendance.KafkaListener;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.Attendance.KafkaListener.DTO.UserDetails;

@Configuration
public class KafkaConfig {
	
	@Value("${spring.kafka.consumer.group-id}")
    private String GROUP_ID_CONFIG;
	
	@Value("${spring.kafka.consumer.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS;
	
	@Bean
	public ConsumerFactory<String,String> consumerFactory(){		
		Map<String,Object> map= new HashMap<>();
		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		map.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(map,new StringDeserializer(),new StringDeserializer());
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> userListener(){
		ConcurrentKafkaListenerContainerFactory<String, String> consumer = new ConcurrentKafkaListenerContainerFactory<>();
		consumer.setConsumerFactory(consumerFactory());
		return consumer;
	}
}
