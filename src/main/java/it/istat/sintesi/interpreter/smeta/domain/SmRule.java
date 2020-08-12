package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the SM_RULE database table.
 * 
 */
@Data
@Entity
@Table(name="SM_RULE")
@NamedQuery(name="SmRule.findAll", query="SELECT s FROM SmRule s")
public class SmRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	private Short active;

	@Column
	private String descr;
	
	@Column
	private String variabile;

	private Integer errcode;

	@Column
	private String code;

	@Column(length=500)
	private String formula;

	private Short rtype;

	//bi-directional many-to-one association to SmRuleset
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RULESET")
	private SmRuleset smRuleset;

	//bi-directional many-to-one association to SmWorkflow
	@OneToMany(mappedBy="smRule")
	private List<SmWorkflow> smWorkflows;

	public SmRule() {
	}

	 
	public SmWorkflow addSmWorkflow(SmWorkflow smWorkflow) {
		getSmWorkflows().add(smWorkflow);
		smWorkflow.setSmRule(this);

		return smWorkflow;
	}

	public SmWorkflow removeSmWorkflow(SmWorkflow smWorkflow) {
		getSmWorkflows().remove(smWorkflow);
		smWorkflow.setSmRule(null);

		return smWorkflow;
	}

}