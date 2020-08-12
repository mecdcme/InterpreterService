package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SM_BUSINESS_STEP database table.
 * 
 */
@Entity
@Table(name="SM_BUSINESS_STEP")
@NamedQuery(name="SmBusinessStep.findAll", query="SELECT s FROM SmBusinessStep s")
public class SmBusinessStep implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=100)
	private String descr;

	@Column(length=50)
	private String nome;

	//bi-directional many-to-many association to SmBusinessProcess
	@ManyToMany
	@JoinTable(
		name="SM_LINK_PROCESS_STEP"
		, joinColumns={
			@JoinColumn(name="BSTEP", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="BPROCESS", nullable=false)
			}
		)
	private List<SmBusinessProcess> smBusinessProcesses;

	//bi-directional many-to-one association to SmRuleset
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REGOLA")
	private SmRuleset smRuleset;

	//bi-directional many-to-many association to SmStepInstance
	@ManyToMany(mappedBy="smBusinessSteps")
	private List<SmStepInstance> smStepInstances;

	//bi-directional many-to-one association to SmWorkflow
	@OneToMany(mappedBy="smBusinessStep1")
	private List<SmWorkflow> smWorkflows1;

	//bi-directional many-to-one association to SmWorkflow
	@OneToMany(mappedBy="smBusinessStep2")
	private List<SmWorkflow> smWorkflows2;

	//bi-directional many-to-one association to SmWorkflow
	@OneToMany(mappedBy="smBusinessStep3")
	private List<SmWorkflow> smWorkflows3;

	public SmBusinessStep() {
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

	public List<SmBusinessProcess> getSmBusinessProcesses() {
		return this.smBusinessProcesses;
	}

	public void setSmBusinessProcesses(List<SmBusinessProcess> smBusinessProcesses) {
		this.smBusinessProcesses = smBusinessProcesses;
	}

	public SmRuleset getSmRuleset() {
		return this.smRuleset;
	}

	public void setSmRuleset(SmRuleset smRuleset) {
		this.smRuleset = smRuleset;
	}

	public List<SmStepInstance> getSmStepInstances() {
		return this.smStepInstances;
	}

	public void setSmStepInstances(List<SmStepInstance> smStepInstances) {
		this.smStepInstances = smStepInstances;
	}

	public List<SmWorkflow> getSmWorkflows1() {
		return this.smWorkflows1;
	}

	public void setSmWorkflows1(List<SmWorkflow> smWorkflows1) {
		this.smWorkflows1 = smWorkflows1;
	}

	public SmWorkflow addSmWorkflows1(SmWorkflow smWorkflows1) {
		getSmWorkflows1().add(smWorkflows1);
		smWorkflows1.setSmBusinessStep1(this);

		return smWorkflows1;
	}

	public SmWorkflow removeSmWorkflows1(SmWorkflow smWorkflows1) {
		getSmWorkflows1().remove(smWorkflows1);
		smWorkflows1.setSmBusinessStep1(null);

		return smWorkflows1;
	}

	public List<SmWorkflow> getSmWorkflows2() {
		return this.smWorkflows2;
	}

	public void setSmWorkflows2(List<SmWorkflow> smWorkflows2) {
		this.smWorkflows2 = smWorkflows2;
	}

	public SmWorkflow addSmWorkflows2(SmWorkflow smWorkflows2) {
		getSmWorkflows2().add(smWorkflows2);
		smWorkflows2.setSmBusinessStep2(this);

		return smWorkflows2;
	}

	public SmWorkflow removeSmWorkflows2(SmWorkflow smWorkflows2) {
		getSmWorkflows2().remove(smWorkflows2);
		smWorkflows2.setSmBusinessStep2(null);

		return smWorkflows2;
	}

	public List<SmWorkflow> getSmWorkflows3() {
		return this.smWorkflows3;
	}

	public void setSmWorkflows3(List<SmWorkflow> smWorkflows3) {
		this.smWorkflows3 = smWorkflows3;
	}

	public SmWorkflow addSmWorkflows3(SmWorkflow smWorkflows3) {
		getSmWorkflows3().add(smWorkflows3);
		smWorkflows3.setSmBusinessStep3(this);

		return smWorkflows3;
	}

	public SmWorkflow removeSmWorkflows3(SmWorkflow smWorkflows3) {
		getSmWorkflows3().remove(smWorkflows3);
		smWorkflows3.setSmBusinessStep3(null);

		return smWorkflows3;
	}

}