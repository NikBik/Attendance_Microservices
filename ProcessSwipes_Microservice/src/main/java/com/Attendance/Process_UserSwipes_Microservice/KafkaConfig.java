package com.Attendance.Process_UserSwipes_Microservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
	
	@Value("${topic.name}")
	private String topicName;
	
	public NewTopic attendanceTopic() {
		return TopicBuilder.name(topicName).build();
		/*return new NewTopic(topicName, 5, 3);
		 * 5 - 5 partitions of topic will be created
		 * 3- for each partition, 10 replicas are distributed across available brokers
		 * It means, that within available brokers in your cluster, 15 replicas of topic will be created.
		 * If I host Kafka on 2 servers, these 15 replicas will be shared across 2 servers    
		 * */
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
}
