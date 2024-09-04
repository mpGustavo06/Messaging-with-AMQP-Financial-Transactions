## Financial transactions with AMQP protocol and RabbitMQ message broker

- This repository contains the projects necessary to perform message production and consumption using the AMQP protocol, together with RabbitMQ.
- The message producer reads a CSV file that contains records of financial transactions, and each transaction is sent individually to a queue.
- The consumer consumes these messages and checks whether the transaction exceeds a certain value. 
- If it does, the transaction will be sent to government agencies through a Fonout-type Exchange to verify authenticity.