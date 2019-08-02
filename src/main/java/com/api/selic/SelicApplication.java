package com.api.selic;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.api.selic.domain.Taxa;
import com.api.selic.service.TaxaService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
public class SelicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelicApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(TaxaService taxaService){
	    return args -> {
			ObjectMapper mapper = new ObjectMapper();
			  
	        mapper.registerModule(new JavaTimeModule());
	        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	        mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
	        
			TypeReference<List<Taxa>> typeReference = new TypeReference<List<Taxa>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/selic.json");
			
			try {
				List<Taxa> taxas = mapper.readValue(inputStream,typeReference);
				taxaService.save(taxas);
				System.out.println("Taxas Saved!");
			} catch (IOException e){
				System.out.println("Error while saving taxas: " + e.getMessage());
			}
	    };
	}
	
}
