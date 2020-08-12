/**
 * 
 */
package it.istat.sintesi.interpreter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.istat.sintesi.interpreter.smeta.domain.SmEdizione;
import it.istat.sintesi.interpreter.smeta.domain.SmFase;
import it.istat.sintesi.interpreter.smeta.domain.SmUnit;
 



/**
 * @author framato
 *
 */
@Repository
public interface SmUnitDAO  extends JpaRepository<SmUnit, Long> {
 
	@Query(value="SELECT  uef.smUnit from  SmUnitEditionFase   uef where   uef.smEdizione=:edizione  and  uef.smFase= :fase  ")
	 public  SmUnit findUnitByEdizioneAndFase(@Param("edizione")SmEdizione edizione,@Param("fase")SmFase fase);
}
