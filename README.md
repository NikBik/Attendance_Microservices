# Attendance_Microservices
This is Attendance system used to record employee's attendance daily.
Consists of three main microservices:
1. **UserSwipes_Microservice** - First Level of microservice that receive any swipe request & validates it.
2. **ProcessSwipes_Microservice** - Upon successful validation of the request, the request is delegated to this microservice for persisting swipe details in DB. It also trigger job at the End of the Day.
3. **KafkaListener_Microservice** - At the EOD, ProcessSwipes_Microservice triggers CRON job posts day's attendance to Kafka which in turn is consumed by this microservice and is stored in the database.

This architecture is supported by other services like:
* **Eureka_Server_Microservice** - For centralized registry of instances of all the microservices.
* **Config_Server_Microservice** - This serves as centralized location to keep application properties for various environments of all microservices.
* **API_Gateway_Microservice** - Serves as the entry point of application where all requests are validated against OKTA.

Additionally, it uses below supporting technologies:
* **MY_SQL_DataBase** - To store all swipe and attendance details.
* **Prometheus_Server** - Fetches health details from all instances of three microservices.
* **Grafana_Dashboard** - Displays health details of various instances of microservices using Prometheus as datasource.

All microservices have been turned into Docker Images & to deploy them, a docker-compose file with different restart policy is written.

