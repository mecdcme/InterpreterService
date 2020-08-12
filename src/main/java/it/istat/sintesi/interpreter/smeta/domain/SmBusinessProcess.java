package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SM_BUSINESS_PROCESS database table.
 * 
 */
@Entity
@Table(name="SM_BUSINESS_PROCESS")
@NamedQuery(name="SmBusinessProcess.findAll", query="SELECT s FROM SmBusinessProcess s")
public class SmBusinessProcess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=100)
	private String descr;

	@Column(length=100)
	private String etichetta;

	@Column(length=50)
	private String nome;

	//bi-directional many-to-many association to SmBusinessFunction
	@ManyToMany
	@JoinTable(
		name="SM_LINK_FUNCTION_PROCESS"
		, joinColumns={
			@JoinColumn(name="BPROCESS", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="BFUNCTION", nullable=false)
			}
		)
	private List<SmBusinessFunction> smBusinessFunctions;

	//bi-directional many-to-one association to SmRuleset
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REGOLE")
	private SmRuleset smRuleset;

	//bi-directional many-to-many association to SmBusinessStep
	@ManyToMany(mappedBy="smBusinessProcesses")
	private List<SmBusinessStep> smBusinessSteps;

	public SmBusinessProcess() {
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

	public String getEtichetta() {
		return this.etichetta;
	}

	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<SmBusinessFunction> getSmBusinessFunctions() {
		return this.smBusinessFunctions;
	}

	public void setSmBusinessFunctions(List<SmBusinessFunction> smBusinessFunctions) {
		this.smBusinessFunctions = smBusinessFunctions;
	}

	public SmRuleset getSmRuleset() {
		return this.smRuleset;
	}

	public void setSmRuleset(SmRuleset smRuleset) {
		this.smRuleset = smRuleset;
	}

	public List<SmBusinessStep> getSmBusinessSteps() {
		return this.smBusinessSteps;
	}

	public void setSmBusinessSteps(List<SmBusinessStep> smBusinessSteps) {
		this.smBusinessSteps = smBusinessSteps;
	}

}