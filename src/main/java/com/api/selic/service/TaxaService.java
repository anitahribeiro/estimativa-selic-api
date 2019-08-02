package com.api.selic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.selic.domain.Taxa;
import com.api.selic.repository.TaxaRepository;



@Service
public class TaxaService {

	private TaxaRepository taxaRepository;
	
	public TaxaService(TaxaRepository taxaRepository) {
		this.taxaRepository = taxaRepository;
	}
	
	public Iterable<Taxa> list(int ano, Integer mes) {
		
		List<Taxa> listaTaxa = new ArrayList<>();
		boolean hasMes = false;
		
		if (mes != null && mes >= 1 && mes <=12) {
			hasMes = true;
		}
				
		for(Taxa taxa : taxaRepository.findAll()){
			if (taxa.getAnoEstimativa() == ano) {
				if (hasMes) {
					if (taxa.getMesEstimativa() == mes) {
						listaTaxa.add(taxa);
						continue;
					} else {
						continue;
					}
				}
		
				listaTaxa.add(taxa);
			}
		}
		
		return listaTaxa;
		
	}
	
	
public Double getMedia(int ano) {
		
		double media = 0;
		double sum = 0;
		int count = 0;
		
		for(Taxa taxa : taxaRepository.findAll()){
			if (taxa.getAnoEstimativa() == ano) {
				sum += taxa.getEstimativaTaxaSelic();
				count++;
			}
		}
		
		media = sum/count;
		
		return media;
		
	}
	
	
	public Taxa save(Taxa taxa) {
		return taxaRepository.save(taxa);
	}
	
	public void save(List<Taxa> taxas){
		taxaRepository.save(taxas);
	}
	
}
