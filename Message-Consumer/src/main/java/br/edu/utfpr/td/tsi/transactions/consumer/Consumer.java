package br.edu.utfpr.td.tsi.transactions.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

@Component
public class Consumer {
	@RabbitListener(queues = {"financial.transactions"})
	public void listen(String transacao) {
		this.processarTransacao(transacao);
	}
	
	public void processarTransacao(String transacao) {
		String tr = this.jsonConvert(transacao);
		
		try {
			System.out.println(tr);
			Thread.sleep(5L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	public String jsonConvert(String transacao) {
		Gson gson = new Gson();  
		Transacao tr = gson.fromJson(transacao, Transacao.class);  
		
		return tr.toString();
	}
}
