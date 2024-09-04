package br.edu.utfpr.td.tsi.transactions.producer;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVFormat.Builder;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class LeitorCSV {
	public List<Transacao> lerCSV() {
		List<Transacao> transacoes = new ArrayList<>();

		try {
			Reader readerCSV = new FileReader("/transacoes.csv");
			CSVFormat configCSV = Builder.create()
					.setHeader(new String[] { "codigo", "cedente", "pagador", "valor", "vencimento" })
					.setSkipHeaderRecord(true).build();

			CSVParser interpreterCSV = configCSV.parse(readerCSV);

			List<CSVRecord> registers = interpreterCSV.getRecords();

			for (CSVRecord register : registers) {
				Transacao trc = new Transacao(register.get("codigo"), register.get("cedente"), register.get("pagador"),
						Double.valueOf(register.get("codigo")), register.get("vencimento"));
				transacoes.add(trc);
			}
		} catch (Exception e) {
			System.out.println("Erro ao abrir o arquivo CSV!");
			System.out.println("Favor colocar o arquivo 'transacoes.csv' encontrado em resourcer no diretório C:/");
		}

		return transacoes;
	}
}
