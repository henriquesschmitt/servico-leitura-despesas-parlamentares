/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servico.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.sql.Date;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.service.dominio.Despesa;

/**
 *
 * @author Henrique
 */
public class ReadAndPersistFile {
	
	public static final Charset UTF_8 = Charset.forName("UTF-8");
	public static final Charset ISO = Charset.forName("ISO-8859-1");

	public static List<Despesa> readFileExpensesSenado(String fileDir, String tipoParlamentar) {

		List<Despesa> listOfExpenses = new ArrayList<>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";

		try {
			
			System.out.println("LE E GRAVA DESPESAS DO ARQUIVO NA BASE DE DADOS");

			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "ISO-8859-1"));
			br.readLine();
			br.readLine();

			int count = 0;
			
			while ((line = br.readLine()) != null) {

				count++;
				
				try {

					String[] country = line.split(cvsSplitBy);

					String ano = country[0] != null ? country[0].replace("\"", "") : "2016";
					String mes = country[1] != null ? country[1].replace("\"", "") : "01";
					String nome = country[2] != null ? country[2].replace("\"", "") : "";
					String tipoDespesa = country[3] != null ? country[3].replace("\"", "") : "";
					String cpfCnpj = country[4] != null ? country[4].replace("\"", "") : "";
					String fornecedor = country[5] != null ? country[5].replace("\"", "") : "";
					String documento = country[6] != null ? country[6].replace("\"", "") : "";

					DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
					Date data = null;
					try {
						String dataConvertida = country[7].replace("\"", "");
						data = country[7] != null ? new java.sql.Date(fmt.parse(dataConvertida).getTime())
								: new java.sql.Date(new java.util.Date().getTime());
					} catch (ParseException ex) {
						Logger.getLogger(ReadAndPersistFile.class.getName()).log(Level.SEVERE, null, ex);
					}

					String descricaoDespesa = new String(country[8].replace("\"", ""));	
					
					BigDecimal valor = null;
					try {
						
						String valorConvertido = "";
						if(country[9].equals("\"")){
							valorConvertido = country[10].replace("\"", "").replace(",", ".");
						} else {
							valorConvertido = country[9].replace("\"", "").replace(",", ".");
						}
						valor = valorConvertido != null ? new BigDecimal(valorConvertido)
								: new BigDecimal(BigInteger.ZERO);
					
					} catch (Exception e) {
						System.out.println("erro no valor = " + e.getMessage());
					}
										
					Despesa despesa = new Despesa(ano, mes, tipoParlamentar, nome, tipoDespesa, cpfCnpj,
							fornecedor, documento, data, descricaoDespesa, valor);

					listOfExpenses.add(despesa);
				} catch (Exception e) {
					System.out.println("erro: " + e.getMessage());
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return listOfExpenses;
	}
}
