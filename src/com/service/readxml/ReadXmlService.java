package com.service.readxml;

import java.util.List;

import com.service.dao.DespesaDAO;
import com.service.dominio.Despesa;
import com.servico.file.DownloadFile;
import com.servico.file.ReadAndPersistFile;
import com.servico.util.EntityManagerProducer;

public class ReadXmlService {

    public static void main(String[] args) {
                
//        /** BAIXA E SALVA O ARQUIVO DE DESPESAS DO SENADO FEDERAL **/
//        DownloadFile.downloadAndSaveFile("http://www.senado.gov.br/transparencia/LAI/verba/2016.csv", "senado.csv");
//        
//        /** LE E GRAVA DESPESAS DO ARQUIVO NA BASE DE DADOS **/
//        List<Despesa> expensesList = ReadAndPersistFile.readFileExpensesSenado("./arquivos/senado.csv", "s");
//        
        EntityManagerProducer entityManagerProducer = new EntityManagerProducer();
//        DespesaDAO despesaDAO = new DespesaDAO();
//        
//        /** LIMPA A TABELA ANTES DE PERSISTIR OS DADOS **/
//        despesaDAO.excluir(entityManagerProducer.create());        
//                
//        /** GRAVA TODAS AS DESPESAS LIDAS DO PORTAL DA TRANSPARENCIA NA BASE DE DADOS **/
//        System.out.println("GRAVA TODAS AS DESPESAS LIDAS DO PORTAL DA TRANSPARENCIA NA BASE DE DADOS");
//        for(Despesa despesa_corrente : expensesList){
//        	despesaDAO.salvar(entityManagerProducer.create(), despesa_corrente);
//        }       
    	
    	GetDadosParlamentares dadosParlamentares = new GetDadosParlamentares();
    	dadosParlamentares.sendGetParlamentaresDados(entityManagerProducer.create());
    	
    }    
}
