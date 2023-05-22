package com.globaltek.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.globaltek.springboot.app.models.dao.IFacturaDao;
import com.globaltek.springboot.app.models.entity.Factura;

@Controller
@SessionAttributes("factura")
public class FacturaController {

	@Autowired
	private IFacturaDao facturaDao;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de facturas");
		model.addAttribute("facturas", facturaDao.findAll());
		return "listar";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Factura factura = new Factura();
		model.put("factura", factura);
		model.put("titulo", "Formulario de Factura");
		return "form";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Factura factura = null;
		
		if(id > 0) {
			factura = facturaDao.findOne(id);
		} else {
			return "redirect:/listar";
		}
		model.put("factura", factura);
		model.put("titulo", "Editar Factura");
		return "form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Factura factura, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Factura");
			return "form";
		}
		
		facturaDao.save(factura);
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id > 0) {
			facturaDao.delete(id);
		}
		return "redirect:/listar";
	}
}