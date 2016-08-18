/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servico.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.servico.bd.ConexaoBD;

/**
 *
 * @author Henrique
 */
public class ReadAndPersistFile {

    private static PreparedStatement ps;
    private static Connection con;
    private static ResultSet rs;

    public static List<FileObject> readFileExpensesSenado(String fileDir, String tipoParlamentar) {

        List<FileObject> listOfExpenses = new ArrayList<>();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            br = new BufferedReader(new FileReader(fileDir));
            br.readLine();
            br.readLine();
            
            int count = 0;
            
            while ((line = br.readLine()) != null) {
            	
            	count++;
            	
            	if ((line = br.readLine()) == "")
            		continue;
            	
                String[] country = line.split(cvsSplitBy);

                System.out.println(count);
                
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

                String descricaoDespesa = country[8].replace(";", ",").replace("\"", "");

                BigDecimal valor = null;
                try {
                    String valorConvertido = country[9].replace("\"", "").replace(",", ".");
                    valor = valorConvertido != null ? new BigDecimal(valorConvertido) : new BigDecimal(BigInteger.ZERO);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                FileObject despesa = new FileObject(ano, mes, tipoParlamentar, nome, tipoDespesa, cpfCnpj, fornecedor,
                        documento, data, descricaoDespesa, valor);
                listOfExpenses.add(despesa);
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

    public static boolean gravaDespesasNaBase(List<FileObject> expensesList) {
        try {

            con = ConexaoBD.conectar();

            for (FileObject expensesList1 : expensesList) {
                
                ps = con.prepareStatement("insert into despesas(ano, mes, tipo_parlamentar, nome, tipo_despesa, cnpj_cpf, "
                        + "fornecedor, documento, data, descricao_despesa, valor) "
                        + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                
                System.out.println(expensesList1.getNome() + " - " + expensesList1.getDocumento());
                
                ps.setString(1, expensesList1.getAno().replace("\"", ""));
                ps.setString(2, expensesList1.getMes().replace("\"", ""));
                ps.setString(3, expensesList1.getTipoParlamentar());
                ps.setString(4, expensesList1.getNome().replace("\"", ""));
                ps.setString(5, expensesList1.getTipoDespesa().replace("\"", ""));
                ps.setString(6, expensesList1.getCpfCnpj().replace("\"", ""));
                ps.setString(7, expensesList1.getFornecedor().replace("\"", ""));
                ps.setString(8, expensesList1.getDocumento().replace("\"", ""));
                ps.setDate(9, expensesList1.getData());
                ps.setString(10, expensesList1.getDescricaoDespesa().replace("\"", ""));
                ps.setBigDecimal(11, expensesList1.getValor());

                ps.executeUpdate();
            }

            con.close();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static boolean deletarDespesasPassadas() {
        
        System.out.println("deletando despesas passadas...");

        try {

            con = ConexaoBD.conectar();
            ps = con.prepareStatement("delete from despesas");

            ps.executeUpdate();

            con.close();

            System.out.println("despesas deletadas com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("erro ao deletar despesas: " + e.getMessage());
        }

        return false;
    }

}
