package com.api.selic.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MediaTaxa {

	@JsonProperty("ano_referencia")
	private int ano;
	
	@JsonProperty("taxa_media")
	private Double mediaTaxa;

	public MediaTaxa(int ano, double mediaTaxa) {
		super();
		this.ano = ano;
		this.mediaTaxa = mediaTaxa;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Double getMediaTaxa() {
		return mediaTaxa;
	}

	public void setMediaTaxa(double mediaTaxa) {
		this.mediaTaxa = mediaTaxa;
	}

	
}
