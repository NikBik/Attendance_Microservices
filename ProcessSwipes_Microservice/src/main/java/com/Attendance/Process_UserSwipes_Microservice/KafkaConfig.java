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
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
}
