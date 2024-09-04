package br.edu.utfpr.td.tsi.transactions.consumer;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

@Component
public class Consumer {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	private FanoutExchange fanout = new FanoutExchange("suspicious.transactions", true, false);

	@RabbitListener(queues = { "financial.transactions" })
	public void listen(String transacao) {
		this.processarTransacao(transacao);
	}

	public void processarTransacao(String transacao) {
		String tr = this.jsonToObjString(transacao);

		try {
			System.out.println(tr);
			Thread.sleep(5L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public String jsonToObjString(String transacao) {
		Gson gson = new Gson();
		Transacao tr = gson.fromJson(transacao, Transacao.class);

		if (checkValue(tr)) {
			return "Transação " + tr.getCodigo() + " enviada para orgãos responsáveis!";
		} else {
			return tr.toString();
		}
	}

	public boolean checkValue(Transacao transacao) {
		if (transacao.getValor() >= 40000.0) {
			rabbitTemplate.convertAndSend(fanout.getName(), "", objToJsonString(transacao));
			return true;
		} else {
			return false;
		}
	}

	public String objToJsonString(Transacao tr) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(tr);

		return jsonString;
	}
}