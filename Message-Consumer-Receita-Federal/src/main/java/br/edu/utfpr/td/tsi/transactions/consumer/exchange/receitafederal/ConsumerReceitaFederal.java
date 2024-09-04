package br.edu.utfpr.td.tsi.transactions.consumer.exchange.receitafederal;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;


@Component
public class ConsumerReceitaFederal {
	@RabbitListener(queues = {"receita.federal"})
	public void listen(String transacao) {
		this.processarTransacao(transacao);
	}
	
	public void processarTransacao(String transacao) {
		String tr = this.jsonToObjString(transacao);
		
		try {
			System.out.println(tr);
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	public String jsonToObjString(String transacao) {
		Gson gson = new Gson();
		Transacao tr = gson.fromJson(transacao, Transacao.class);
		
		return tr.toString();
	}
}
