package com.IsilERPSpringDAE2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilERPSpringDAE2.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	Usuario findByCorreoAndPassword(String correo, String password);
	List<Usuario> findAll();
	Usuario findById(int id);
	void deleteById(int id);
}
