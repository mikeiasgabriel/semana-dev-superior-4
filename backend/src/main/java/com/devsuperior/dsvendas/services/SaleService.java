package com.devsuperior.dsvendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsvendas.dto.SaleDTO;
import com.devsuperior.dsvendas.dto.SaleSuccessDTO;
import com.devsuperior.dsvendas.dto.SaleSumDTO;
import com.devsuperior.dsvendas.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;
	
	@Transactional(readOnly = true)
	public Page<SaleDTO> findAll(Pageable pageable) {
		return saleRepository
				.findAll(pageable)
				.map(SaleDTO::new);
	}
	
	@Transactional(readOnly = true)
	public List<SaleSumDTO> amountGroupedBySaller() {
		return saleRepository.amountGroupedBySaller();
	}
	
	@Transactional(readOnly = true)
	public List<SaleSuccessDTO> successGroupedBySaller() {
		return saleRepository.successGroupedBySaller();
	}
}
