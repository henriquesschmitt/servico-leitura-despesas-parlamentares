package com.service.readxml;

import java.util.List;

import com.servico.bd.ConexaoBD;
import com.servico.file.DownloadFile;
import com.servico.file.FileObject;
import com.servico.file.ReadAndPersistFile;

public class ReadXmlService {

    public static void main(String[] args) {
        
        /** CONECTAR NA BASE DE DADOS **/
        System.out.println("conectando no banco de dados...");
        ConexaoBD.conectar();
        System.out.println("conexão com banco de dados estabelecida com sucesso!");
        
        /** BAIXA E GRAVA ARQUIVO DE DESPESAS DO SENADO FEDERAL **/
        DownloadFile.downloadAndSaveFile("http://www.senado.gov.br/transparencia/LAI/verba/2016.csv", "senado.csv");
        
        /** LE E GRAVA DESPESAS DO ARQUIVO NA BASE DE DADOS **/
        List<FileObject> expensesList = ReadAndPersistFile.readFileExpensesSenado("./arquivos/senado.csv", "s");
        ReadAndPersistFile.deletarDespesasPassadas();
        ReadAndPersistFile.gravaDespesasNaBase(expensesList);        
    }
    
}
