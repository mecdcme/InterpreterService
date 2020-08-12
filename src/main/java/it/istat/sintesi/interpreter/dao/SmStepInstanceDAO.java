/**
 * 
 */
package it.istat.sintesi.interpreter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.istat.sintesi.interpreter.smeta.domain.SmStepInstance;


/**
 * @author framato
 *
 */
@Repository
public interface SmStepInstanceDAO extends  JpaRepository<SmStepInstance, Long>{
	
	//SmStepInstance findBy
}
