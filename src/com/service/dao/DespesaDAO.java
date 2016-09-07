package com.service.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.service.dominio.Despesa;

public class DespesaDAO implements Serializable {
	
	private static final long serialVersionUID = 5743744469095830923L;

	public void salvar(EntityManager em, Despesa despesa) {
	
		em.getTransaction().begin();
		em.persist(despesa);
		em.getTransaction().commit();
		
		em.close();
	}
	
	public void excluir(EntityManager em) {
		System.out.println("ESVAZIANDO A TABELA COM DESPESAS ANTIGAS");
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM despesa").executeUpdate();
		em.getTransaction().commit();
	}
		
}
