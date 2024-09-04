package br.edu.utfpr.td.tsi.transactions.consumer.exchange.policiafederal;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan({ "br.edu.utfpr.td.tsi.transactions.consumer.exchange.policiafederal" })
public class MessageConsumerPoliciaFederalApplication {
	@Autowired
	private AmqpAdmin amqpAdmin;
	
	public static void main(String[] args) {
		SpringApplication.run(MessageConsumerPoliciaFederalApplication.class, args);
	}

	@PostConstruct
	public void init() {
		Queue queueTransacoes = new Queue("policia.federal", true);
		this.amqpAdmin.declareQueue(queueTransacoes);
	}
}