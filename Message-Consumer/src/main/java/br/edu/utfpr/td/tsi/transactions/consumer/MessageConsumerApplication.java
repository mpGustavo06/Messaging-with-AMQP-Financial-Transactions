package br.edu.utfpr.td.tsi.transactions.consumer;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
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
		
		Queue queuePoliciaFederal = new Queue("policia.federal", true);
		this.amqpAdmin.declareQueue(queuePoliciaFederal);
		
		Queue queueReceitaFederal = new Queue("receita.federal", true);
		this.amqpAdmin.declareQueue(queueReceitaFederal);
		
		FanoutExchange fanout = new FanoutExchange("suspicious.transactions", true, false);
		amqpAdmin.declareExchange(fanout);
		
		Binding binding = BindingBuilder.bind(queuePoliciaFederal).to(fanout);
		amqpAdmin.declareBinding(binding);
		
		binding = BindingBuilder.bind(queueReceitaFederal).to(fanout);
		amqpAdmin.declareBinding(binding);
	}
}