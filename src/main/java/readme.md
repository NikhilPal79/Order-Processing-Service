spring.application.name=order-processing-service
spring.datasource.url=jdbc:h2:mem:appdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true



# Static Scheduling 
1. @Scheduled(fixedRate = 5000) [we use this to start at fixed rate ]  

2. @Scheduled(initialDelay = 1000, fixedDelay = 10000) [use this to have late start and have time between two events]


# Dynamic Scheduling [lets suppose we have sale, and number of customers inc on sale day to order single product]
# [we cant change the code in production environment and redeployed the application][thats where dynamic scheduling comes in picture ]


# for that we can update the cron job in the database so the scheduler can update the cron jobs from database 