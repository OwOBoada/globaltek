package com.globaltek.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.globaltek.springboot.app.models.entity.Factura;

@Repository
public class FacturaDaoImpl implements IFacturaDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Factura> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Factura").getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findOne(Long id) {
		return em.find(Factura.class, id);
	}

	@Override
	@Transactional
	public void save(Factura factura) {
		if (factura.getId() != null && factura.getId() > 0) {
			em.merge(factura);
		} else {
			em.persist(factura);
		}
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
