package com.IsilERPSpringDAE2.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.IsilERPSpringDAE2.entity.OrdenCompra;
import com.IsilERPSpringDAE2.repository.OrdenCompraRepository;

@Controller
@RequestMapping("/ordenCompra")
public class OrdenCompraController {

	@Autowired
	OrdenCompraRepository ordenCompraRepository;
	
	@RequestMapping(value="/buscarOrdenCompra", method=RequestMethod.GET)
	public String buscarProveedor(HttpServletRequest request, @RequestParam(value="fechaInicio") Date fechaInicio, @RequestParam(value="fechaFin") Date fechaFin, Model model) {
		List<OrdenCompra> listaOrdenesCompra = ordenCompraRepository.findByFechaRegistroBetween(fechaInicio, fechaFin);
		model.addAttribute("listaOrdenesCompra", listaOrdenesCompra);
		return "gestionOrdenCompra";
	}
	
}
