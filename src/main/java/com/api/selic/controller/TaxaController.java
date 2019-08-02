package com.api.selic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.selic.domain.Taxa;
import com.api.selic.domain.MediaTaxa;
import com.api.selic.service.TaxaService;

@RestController
@RequestMapping("/selic")
public class TaxaController {

    @Autowired
	private TaxaService taxaService;
	
	public TaxaController(TaxaService taxaService) {
		this.taxaService = taxaService;
	}
	
	//http://localhost:8080/selic/estimativas?ano=2017
	//http://localhost:8080/selic/estimativas?ano=2017&mes=3
	@GetMapping("/estimativas")
	public Iterable<Taxa> list(
			@RequestParam(value = "ano") int ano, 
			@RequestParam (value = "mes", required = false) Integer mes) {
		return taxaService.list(ano, mes);
	}
	
	//http://localhost:8080/selic/media?ano=2017
	@GetMapping("/media")
	public MediaTaxa getMedia(
			@RequestParam(value = "ano") int ano) {
			
		Double media = taxaService.getMedia(ano);
		media = Math.floor(media * 100) / 100;
		
		MediaTaxa response = new MediaTaxa(ano, Double.valueOf(media));
		return response;
	}
	
}
