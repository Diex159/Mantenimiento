package com.IsilERPSpringDAE2.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.IsilERPSpringDAE2.entity.Ciudad;
import com.IsilERPSpringDAE2.entity.OrdenCompra;
import com.IsilERPSpringDAE2.entity.Proveedor;
import com.IsilERPSpringDAE2.entity.Usuario;
import com.IsilERPSpringDAE2.repository.CiudadRepository;
import com.IsilERPSpringDAE2.repository.OrdenCompraRepository;
import com.IsilERPSpringDAE2.repository.ProveedorRepository;
import com.IsilERPSpringDAE2.repository.UsuarioRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	ProveedorRepository proveedorRepository;
	
	@Autowired
	CiudadRepository ciudadRepository;
	
	@Autowired
	OrdenCompraRepository ordenCompraRepository;
	
	@RequestMapping(value="/validarUsuario", method=RequestMethod.POST)
	public String validarUsuario(HttpServletRequest request, @RequestParam(value="email")String email, @RequestParam(value="password")String password) {
		Usuario objUsuario = usuarioRepository.findByCorreoAndPassword(email, password);
		if (objUsuario!=null) {
			return "principal";			
		}
		else {
			return "index";
		}
	}
	
	@GetMapping("/mostrarGestionUsuarios")
	public String mostrarGestionUsuarios(HttpServletRequest request, Model model) {
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "gestionUsuarios";
	}

	@GetMapping("/mostrarGestionProveedores")
	public String mostrarGestionProveedores(HttpServletRequest request, Model model) {
		List<Proveedor> listaProveedores = proveedorRepository.findAll();
		List<Ciudad> listaCiudades = ciudadRepository.findByEstado("Activo");
		model.addAttribute("listaProveedores", listaProveedores);
		model.addAttribute("listaCiudades", listaCiudades);
		return "gestionProveedores";
	}	

	@GetMapping("/mostrarGestionOrdenesCompra")
	public String mostrarGestionOrdenesCompra(HttpServletRequest request, Model model) {
		List<OrdenCompra> listaOrdenesCompra = ordenCompraRepository.findAll();
		model.addAttribute("listaOrdenesCompra", listaOrdenesCompra);
		return "gestionOrdenCompra";
	}
	
}
