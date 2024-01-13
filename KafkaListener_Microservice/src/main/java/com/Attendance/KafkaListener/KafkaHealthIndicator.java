package com.Attendance.KafkaListener;

import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class KafkaHealthIndicator implements HealthIndicator {

	@Value("${spring.kafka.consumer.group-id}")
	private String GROUP_ID_CONFIG;

	@Value("${spring.kafka.consumer.bootstrap-servers}")
	private String BOOTSTRAP_SERVERS;

	@Override
	public Health health() {
		try {
			Properties props = new Properties();
			props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
			props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
			props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			AdminClient adminClient = AdminClient.create(props);
			adminClient.describeCluster();
			return Health.up().build();
		} catch (Exception e) {
			return Health.down().withException(e).build();
		}
	}
}