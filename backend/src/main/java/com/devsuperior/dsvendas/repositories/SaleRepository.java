package com.devsuperior.dsvendas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dsvendas.entities.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {	
	@Query(value = "SELECT s FROM Sale s JOIN FETCH s.seller", countQuery = "SELECT count(s) FROM Sale s")
	Page<Sale> findAll(Pageable pageable);
}
