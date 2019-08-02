package com.api.selic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.selic.domain.Taxa;

@Repository
public interface TaxaRepository extends CrudRepository<Taxa, String>{

}
