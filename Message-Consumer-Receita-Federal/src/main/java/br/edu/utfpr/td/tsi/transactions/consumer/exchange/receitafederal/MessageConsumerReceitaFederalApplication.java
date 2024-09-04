package br.edu.utfpr.td.tsi.transactions.consumer.exchange.receitafederal;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan({ "br.edu.utfpr.td.tsi.transactions.consumer.exchange.receitafederal" })
public class MessageConsumerReceitaFederalApplication {
	@Autowired
	private AmqpAdmin amqpAdmin;
	
	public static void main(String[] args) {
		SpringApplication.run(MessageConsumerReceitaFederalApplication.class, args);
	}

	@PostConstruct
	public void init() {
		Queue queueTransacoes = new Queue("receita.federal", true);
		this.amqpAdmin.declareQueue(queueTransacoes);
	}
}
