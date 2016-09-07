package com.servico.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {

	private EntityManagerFactory factory;

	public EntityManagerProducer() {
		this.factory = Persistence.createEntityManagerFactory("servicoDespesasPU");
	}

	public EntityManager create() {
		return factory.createEntityManager();
	}

	public void close(EntityManager manager) {
		manager.close();
	}
	
}
