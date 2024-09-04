package br.edu.utfpr.td.tsi.transactions.consumer;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan({ "br.edu.utfpr.td.tsi.transactions.consumer" })
public class MessageConsumerApplication {
	@Autowired
	private AmqpAdmin amqpAdmin;

	public static void main(String[] args) {
		SpringApplication.run(MessageConsumerApplication.class, args);
	}
	
	@PostConstruct
	public void config() {
		Queue queueTransacoes = new Queue("financial.transactions", true);
		this.amqpAdmin.declareQueue(queueTransacoes);
	}
}