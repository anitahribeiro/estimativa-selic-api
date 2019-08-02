package com.api.selic.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.selic.domain.Taxa;
import com.api.selic.domain.AcumuladaTaxa;
import com.api.selic.domain.MediaTaxa;
import com.api.selic.service.TaxaService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/selic")
public class TaxaController {

    @Autowired
	private TaxaService taxaService;
	
	public TaxaController(TaxaService taxaService) {
		this.taxaService = taxaService;
	}
	
	//http://localhost:8080/api/v1/selic/historico
	@GetMapping("/historico")
	public Iterable<Taxa> listHistory() {
		return taxaService.listHistory();
	}
	
	//http://localhost:8080/api/v1/selic/estimativas?ano=2017
	//http://localhost:8080/api/v1/selic/estimativas?ano=2017&mes=3
	@GetMapping("/estimativas")
	public Iterable<Taxa> list(
			@RequestParam(value = "ano") int ano, 
			@RequestParam (value = "mes", required = false) Integer mes) {
		return taxaService.list(ano, mes);
	}
	
	//http://localhost:8080/api/v1/selic/media
	@GetMapping("/media")
	public ArrayList<MediaTaxa> getMediaTaxas(){
		return taxaService.listAverage();
	}
	
	//http://localhost:8080/api/v1/selic/acumulado
	@GetMapping("/acumulado")
	public ArrayList<AcumuladaTaxa> getAcumulado(){
		return taxaService.listCumulative();
	}
	
}
