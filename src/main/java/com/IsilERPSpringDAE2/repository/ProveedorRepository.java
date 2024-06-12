package com.IsilERPSpringDAE2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilERPSpringDAE2.entity.Ciudad;
import com.IsilERPSpringDAE2.entity.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Integer>{
	List<Proveedor> findAll();
	List<Proveedor> findByCiudad(Ciudad ciudad);
	void deleteById(int id);
	Proveedor findByRuc(String ruc);
	Proveedor findById(int id);
}
