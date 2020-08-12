package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the SM_RULESET database table.
 * 
 */
@Entity
@Table(name="SM_RULESET")
@NamedQuery(name="SmRuleset.findAll", query="SELECT s FROM SmRuleset s")
public class SmRuleset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=50)
	private String descr;

	@Column(length=50)
	private String nome;

	private BigDecimal tipo;

	//bi-directional many-to-one association to SmBusinessProcess
	@OneToMany(mappedBy="smRuleset")
	private List<SmBusinessProcess> smBusinessProcesses;

	//bi-directional many-to-one association to SmBusinessStep
	@OneToMany(mappedBy="smRuleset")
	private List<SmBusinessStep> smBusinessSteps;

	//bi-directional many-to-one association to SmRule
	@OneToMany(mappedBy="smRuleset")
	private List<SmRule> smRules;

	public SmRuleset() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getTipo() {
		return this.tipo;
	}

	public void setTipo(BigDecimal tipo) {
		this.tipo = tipo;
	}

	public List<SmBusinessProcess> getSmBusinessProcesses() {
		return this.smBusinessProcesses;
	}

	public void setSmBusinessProcesses(List<SmBusinessProcess> smBusinessProcesses) {
		this.smBusinessProcesses = smBusinessProcesses;
	}

	public SmBusinessProcess addSmBusinessProcess(SmBusinessProcess smBusinessProcess) {
		getSmBusinessProcesses().add(smBusinessProcess);
		smBusinessProcess.setSmRuleset(this);

		return smBusinessProcess;
	}

	public SmBusinessProcess removeSmBusinessProcess(SmBusinessProcess smBusinessProcess) {
		getSmBusinessProcesses().remove(smBusinessProcess);
		smBusinessProcess.setSmRuleset(null);

		return smBusinessProcess;
	}

	public List<SmBusinessStep> getSmBusinessSteps() {
		return this.smBusinessSteps;
	}

	public void setSmBusinessSteps(List<SmBusinessStep> smBusinessSteps) {
		this.smBusinessSteps = smBusinessSteps;
	}

	public SmBusinessStep addSmBusinessStep(SmBusinessStep smBusinessStep) {
		getSmBusinessSteps().add(smBusinessStep);
		smBusinessStep.setSmRuleset(this);

		return smBusinessStep;
	}

	public SmBusinessStep removeSmBusinessStep(SmBusinessStep smBusinessStep) {
		getSmBusinessSteps().remove(smBusinessStep);
		smBusinessStep.setSmRuleset(null);

		return smBusinessStep;
	}

	public List<SmRule> getSmRules() {
		return this.smRules;
	}

	public void setSmRules(List<SmRule> smRules) {
		this.smRules = smRules;
	}

	public SmRule addSmRule(SmRule smRule) {
		getSmRules().add(smRule);
		smRule.setSmRuleset(this);

		return smRule;
	}

	public SmRule removeSmRule(SmRule smRule) {
		getSmRules().remove(smRule);
		smRule.setSmRuleset(null);

		return smRule;
	}

}