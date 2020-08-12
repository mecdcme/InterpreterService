package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.math.BigDecimal;


/**
 * The persistent class for the SM_WORKFLOW database table.
 * 
 */
@Data
@Entity
@Table(name="SM_WORKFLOW")
@NamedQuery(name="SmWorkflow.findAll", query="SELECT s FROM SmWorkflow s")
public class SmWorkflow implements Serializable {
	private static final long serialVersionUID = 1L;
 
	private Short active;

	private Integer livello;

	//bi-directional many-to-one association to SmBusinessStep
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ECCEZIONE")
	private SmBusinessStep smBusinessStep1;

	//bi-directional many-to-one association to SmBusinessStep
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="STEP")
	private SmBusinessStep smBusinessStep2;

	//bi-directional many-to-one association to SmBusinessStep
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ACTION")
	private SmBusinessStep smBusinessStep3;

	//bi-directional many-to-one association to SmRule
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REGOLA")
	private SmRule smRule;

	public SmWorkflow() {
	}
 
}