/**
 * 
 */
package it.istat.sintesi.interpreter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.istat.sintesi.interpreter.smeta.domain.SmWorkset;

/**
 * @author framato
 *
 */
public interface SmWorksetDAO extends  JpaRepository<SmWorkset,Long>{
	
	 
}
