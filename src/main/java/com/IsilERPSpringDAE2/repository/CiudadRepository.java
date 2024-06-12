package com.IsilERPSpringDAE2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilERPSpringDAE2.entity.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad,Integer> {
	List<Ciudad> findByEstado(String estado);
	Ciudad findById(int id);
}
