package br.edu.utfpr.td.tsi.transactions.producer;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan({ "br.edu.utfpr.td.tsi.transactions.producer" })
public class MessageProducerApplication {
	@Autowired
	private AmqpAdmin amqpAdmin;
	@Autowired
	private Producer pd;
	private Queue queueTransacoes;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MessageProducerApplication.class, args);
	}

	@PostConstruct
	public void init() {
		this.queueTransacoes = new Queue("financial.transactions", true);
		this.amqpAdmin.declareQueue(this.queueTransacoes);
		
		pd.processarTransacoes(queueTransacoes);
	}
}