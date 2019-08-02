package com.api.selic.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Taxa {

	@Id
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonProperty("data_referencia")
    private LocalDate dataReferencia;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	@JsonProperty("estimativa_taxa_selic")
	private Double estimativaTaxaSelic;
	
	public Taxa () {}

	public LocalDate getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(LocalDate dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public Double getEstimativaTaxaSelic() {
		return estimativaTaxaSelic;
	}

	public void setEstimativaTaxaSelic(Double estimativaTaxaSelic) {
		this.estimativaTaxaSelic = estimativaTaxaSelic;
	}

	public int getAnoEstimativa() {
		return dataReferencia.getYear();
	}
	
	public int getMesEstimativa() {
		return dataReferencia.getMonthValue();
	}
	
}

