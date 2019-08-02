package com.api.selic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.selic.domain.AcumuladaTaxa;
import com.api.selic.domain.MediaTaxa;
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
	
	public Taxa save(Taxa taxa) {
		return taxaRepository.save(taxa);
	}
	
	public void save(List<Taxa> taxas){
		taxaRepository.save(taxas);
	}

	public Iterable<Taxa> listHistory() {
		return taxaRepository.findAll();
	}
	
	public ArrayList<AcumuladaTaxa> listCumulative() {
		Map<Integer, Double> map = ((Collection<Taxa>) taxaRepository.findAll()).stream().collect(
                Collectors.groupingBy(Taxa::getAnoEstimativa, Collectors.summingDouble(Taxa::getEstimativaTaxaSelic)));
		
		ArrayList<AcumuladaTaxa> acumuladaTaxa = new ArrayList<>();
		
		for (Map.Entry<Integer, Double> entry : map.entrySet()) {
	        AcumuladaTaxa at = new AcumuladaTaxa(entry.getKey(), entry.getValue());
	        acumuladaTaxa.add(at);
	    }
		
		return acumuladaTaxa;
	}
	
	
	public ArrayList<MediaTaxa> listAverage() {
		Map<Integer, Double> map = ((Collection<Taxa>) taxaRepository.findAll()).stream().collect(
                Collectors.groupingBy(Taxa::getAnoEstimativa, Collectors.summingDouble(Taxa::getEstimativaTaxaSelic)));
		
		ArrayList<MediaTaxa> mediaTaxa = new ArrayList<>();
		
		for (Map.Entry<Integer, Double> entry : map.entrySet()) {
	        MediaTaxa at = new MediaTaxa(entry.getKey(), entry.getValue());
	        mediaTaxa.add(at);
	    }
		
		return mediaTaxa;
	}

	
}
