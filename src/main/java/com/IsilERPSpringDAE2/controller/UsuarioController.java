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
import org.springframework.web.bind.annotation.RequestParam;

import com.IsilERPSpringDAE2.entity.Usuario;
import com.IsilERPSpringDAE2.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(HttpServletRequest request, @PathVariable("id") int id, Model model) {
		usuarioRepository.deleteById(id);
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "gestionUsuarios";
	}
	
	@GetMapping("/mostrarEditar/{id}")
	public String mostrarEditar(HttpServletRequest request, @PathVariable("id") int id, Model model) {
		Usuario objUsuario = usuarioRepository.findById(id);
		model.addAttribute("objUsuario", objUsuario);
		return "editarUsuario";
	}
	
	@PostMapping("/mostrarNuevo")
	public String mostrarNuevo(HttpServletRequest request, Model model) {
		Usuario objUsuario = new Usuario();
		model.addAttribute("objUsuario",objUsuario);
		return "nuevoUsuario";
	}
	
	@PostMapping("/registrar")
	public String registrar(HttpServletRequest request, @ModelAttribute("objUsuario")Usuario objUsuario, Model model) {
		usuarioRepository.save(objUsuario);
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "gestionUsuarios";
	}
	
	@PostMapping("/actualizar")
	public String actualizar(HttpServletRequest request, @ModelAttribute("objUsuario")Usuario objUsuario, Model model) {
		Usuario objUsuarioBD = usuarioRepository.findById(objUsuario.getId());
		objUsuarioBD.setCorreo(objUsuario.getCorreo());
		objUsuarioBD.setEstado(objUsuario.getEstado());
		objUsuarioBD.setPassword(objUsuario.getPassword());
		usuarioRepository.save(objUsuarioBD);
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		model.addAttribute("listaUsuarios", listaUsuarios);		
		return "gestionUsuarios";
	}
}