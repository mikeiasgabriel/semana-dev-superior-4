package com.devsuperior.dsvendas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsvendas.dto.SaleDTO;
import com.devsuperior.dsvendas.dto.SaleSuccessDTO;
import com.devsuperior.dsvendas.dto.SaleSumDTO;
import com.devsuperior.dsvendas.services.SaleService;

@RestController
@RequestMapping(value="/sales")
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	
	@GetMapping
	public ResponseEntity<Page<SaleDTO>> findAll(Pageable pageable) {		
		return ResponseEntity.ok(saleService.findAll(pageable));
	}
	
	@GetMapping("/amount-by-seller")
	public ResponseEntity<List<SaleSumDTO>> amountGroupedBySaller() {
		return ResponseEntity.ok(saleService.amountGroupedBySaller());
	}
	
	@GetMapping("/success-by-seller")
	public ResponseEntity<List<SaleSuccessDTO>> successGroupedBySaller() {
		return ResponseEntity.ok(saleService.successGroupedBySaller());
	}
}
