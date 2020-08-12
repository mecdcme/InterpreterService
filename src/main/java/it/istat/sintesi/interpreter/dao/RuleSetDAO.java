/**
 * 
 */
package it.istat.sintesi.interpreter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.istat.sintesi.interpreter.smeta.domain.SmRuleset;

/**
 * @author framato
 *
 */
@Repository
public interface RuleSetDAO extends JpaRepository<SmRuleset,Long> {

}
