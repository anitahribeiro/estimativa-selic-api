package com.api.selic.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcumuladaTaxa {

	@JsonProperty("ano_referencia")
	private int ano;
	
	@JsonProperty("taxa_acumulada")
	private Double acumuladaTaxa;

	public AcumuladaTaxa(int ano, double acumuladaTaxa) {
		super();
		this.ano = ano;
		this.acumuladaTaxa = acumuladaTaxa;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Double getAcumuladaTaxa() {
		return Math.floor(acumuladaTaxa * 100) / 100;
	}

	public void setAcumuladaTaxa(double acumuladaTaxa) {
		this.acumuladaTaxa = acumuladaTaxa;
	}

	
}

