package br.edu.utfpr.td.tsi.transactions.producer;

import java.util.List;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

@Component
public class Producer {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void processarTransacoes(Queue queueTransacoes) {
		List<Transacao> transacoes = (new LeitorCSV()).lerCSV();

		for (Transacao transacao : transacoes) {
			String tr = jsonToString(transacao);
			
			System.out.println("TESTE"+tr);
			rabbitTemplate.convertAndSend(queueTransacoes.getName(), tr);
		}
	}
	
	public String jsonToString(Transacao tr) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(tr);
		
		return jsonString;
	}
}