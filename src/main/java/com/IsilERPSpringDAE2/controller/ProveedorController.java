package com.IsilERPSpringDAE2.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.IsilERPSpringDAE2.entity.Ciudad;
import com.IsilERPSpringDAE2.entity.Proveedor;
import com.IsilERPSpringDAE2.repository.CiudadRepository;
import com.IsilERPSpringDAE2.repository.ProveedorRepository;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
	
	@Autowired
	CiudadRepository ciudadRepository;
	
	@Autowired
	ProveedorRepository proveedorRepository;
	
	@RequestMapping(value="/buscarProveedor", method=RequestMethod.GET)
	public String buscarProveedor(HttpServletRequest request, @RequestParam(value="idCiudad") int idCiudad, Model model) {
		Ciudad objCiudad = ciudadRepository.findById(idCiudad);
		List<Proveedor> listaProveedores = proveedorRepository.findByCiudad(objCiudad);
		model.addAttribute("listaProveedores", listaProveedores);
		List<Ciudad> listaCiudades = ciudadRepository.findByEstado("Activo");
		model.addAttribute("listaCiudades", listaCiudades);
		model.addAttribute("idCiudadSeleccionada", idCiudad);
		return "gestionProveedores";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(HttpServletRequest request, @PathVariable("id") int id) {
		proveedorRepository.deleteById(id);
		return "redirect:/home/mostrarGestionProveedores";
	}
	
	@GetMapping("/mostrarEditar/{id}")
	public String mostrarEditar(HttpServletRequest request, @PathVariable("id") int id, Model model) {
		Proveedor objProveedor = proveedorRepository.findById(id);
		model.addAttribute("objProveedor", objProveedor);
		List<Ciudad> listaCiudades = ciudadRepository.findByEstado("Activo");
		model.addAttribute("listaCiudades", listaCiudades);
		return "editarProveedor";
	}
	
	@PostMapping("/mostrarNuevo")
	public String mostrarNuevo(HttpServletRequest request, Model model) {
		Proveedor objProveedor = new Proveedor();
		List<Ciudad> listaCiudades = ciudadRepository.findByEstado("Activo");
		model.addAttribute("listaCiudades", listaCiudades);
		model.addAttribute("objProveedor", objProveedor);
		return "nuevoProveedor";
	}
	
	@PostMapping("/registrar")
	public String registar(HttpServletRequest request, @ModelAttribute("objProveedor")Proveedor objProveedor, Model model) {
		Proveedor objProveedorBD = proveedorRepository.findByRuc(objProveedor.getRuc());
		if (objProveedorBD!=null) {
			model.addAttribute("mensaje", "El proveedor ya se encuentra registrado");
			model.addAttribute("objProveedor", objProveedor);
			List<Ciudad> listaCiudades = ciudadRepository.findByEstado("Activo");
			model.addAttribute("listaCiudades", listaCiudades);
			return "nuevoProveedor";
		}
		else {
			proveedorRepository.save(objProveedor);
			return "redirect:/home/mostrarGestionProveedores";
		}
	}
	
	@PostMapping("/actualizar")
	public String actualizar(HttpServletRequest request, @ModelAttribute("objProveedor")Proveedor objProveedor, Model model) {
		Proveedor objProveedorBD =  proveedorRepository.findById(objProveedor.getId());
		objProveedorBD.setRazonSocial(objProveedor.getRazonSocial());
		objProveedorBD.setRuc(objProveedor.getRuc());
		objProveedorBD.setDireccion(objProveedor.getDireccion());
		objProveedorBD.setEstado(objProveedor.getEstado());
		objProveedorBD.setCiudad(objProveedor.getCiudad());
		proveedorRepository.save(objProveedorBD);
		return "redirect:/home/mostrarGestionProveedores";
	}
}
