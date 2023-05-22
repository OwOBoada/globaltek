package com.globaltek.springboot.app.models.dao;

import java.util.List;

import com.globaltek.springboot.app.models.entity.Factura;

public interface IFacturaDao {
	
	public List<Factura> findAll();

	public void save(Factura factura);
	
	public Factura findOne(Long id);
	
	public void delete(Long id);

}
