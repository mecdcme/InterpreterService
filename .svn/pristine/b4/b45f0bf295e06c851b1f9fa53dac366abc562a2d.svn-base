package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SM_BUSINESS_FUNCTION database table.
 * 
 */
@Entity
@Table(name="SM_BUSINESS_FUNCTION")
@NamedQuery(name="SmBusinessFunction.findAll", query="SELECT s FROM SmBusinessFunction s")
public class SmBusinessFunction implements Serializable {
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

	//bi-directional many-to-many association to SmBusinessProcess
	@ManyToMany(mappedBy="smBusinessFunctions")
	private List<SmBusinessProcess> smBusinessProcesses;

	//bi-directional many-to-one association to SmLinkEdizioneFunction
	@OneToMany(mappedBy="smBusinessFunction")
	private List<SmLinkEdizioneFunction> smLinkEdizioneFunctions;

	public SmBusinessFunction() {
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

	public List<SmBusinessProcess> getSmBusinessProcesses() {
		return this.smBusinessProcesses;
	}

	public void setSmBusinessProcesses(List<SmBusinessProcess> smBusinessProcesses) {
		this.smBusinessProcesses = smBusinessProcesses;
	}

	public List<SmLinkEdizioneFunction> getSmLinkEdizioneFunctions() {
		return this.smLinkEdizioneFunctions;
	}

	public void setSmLinkEdizioneFunctions(List<SmLinkEdizioneFunction> smLinkEdizioneFunctions) {
		this.smLinkEdizioneFunctions = smLinkEdizioneFunctions;
	}

	public SmLinkEdizioneFunction addSmLinkEdizioneFunction(SmLinkEdizioneFunction smLinkEdizioneFunction) {
		getSmLinkEdizioneFunctions().add(smLinkEdizioneFunction);
		smLinkEdizioneFunction.setSmBusinessFunction(this);

		return smLinkEdizioneFunction;
	}

	public SmLinkEdizioneFunction removeSmLinkEdizioneFunction(SmLinkEdizioneFunction smLinkEdizioneFunction) {
		getSmLinkEdizioneFunctions().remove(smLinkEdizioneFunction);
		smLinkEdizioneFunction.setSmBusinessFunction(null);

		return smLinkEdizioneFunction;
	}

}