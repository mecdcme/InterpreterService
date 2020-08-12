package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the SM_VARIABLE database table.
 * 
 */
@Data
@Entity
@Table(name="SM_VARIABLE")
@NamedQuery(name="SmVariable.findAll", query="SELECT s FROM SmVariable s")
public class SmVariable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="DEFINIZIONE_EN", length=4000)
	private String definizioneEn;

	@Column(name="DEFINIZIONE_IT", length=4000)
	private String definizioneIt;

	@Column(name="ID_VARIABLE", nullable=false, length=150)
	private String idVariable;

	@Column(name="NOME_VARIABLE_EN", length=4000)
	private String nomeVariableEn;

	@Column(name="NOME_VARIABLE_IT", nullable=false, length=4000)
	private String nomeVariableIt;

	@Column(length=150)
	private String raggruppamento;

	@Column(name="TIPO_VARIABILE", nullable=false, length=6)
	private String tipoVariabile;

	//bi-directional many-to-one association to SmEdizUnitVar
	@OneToMany(mappedBy="smVariable")
	private List<SmEdizUnitVar> smEdizUnitVars;

	//bi-directional many-to-one association to SmInstanceVariable
	@OneToMany(mappedBy="smVariable")
	private List<SmInstanceVariable> smInstanceVariables;

	//bi-directional many-to-one association to SmVariabileType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TIPO_VARIABILE_ID", nullable=false)
	private SmVariabileType smVariabileType;

	public SmVariable() {
	}

	 
	public SmEdizUnitVar addSmEdizUnitVar(SmEdizUnitVar smEdizUnitVar) {
		getSmEdizUnitVars().add(smEdizUnitVar);
		smEdizUnitVar.setSmVariable(this);

		return smEdizUnitVar;
	}

	public SmEdizUnitVar removeSmEdizUnitVar(SmEdizUnitVar smEdizUnitVar) {
		getSmEdizUnitVars().remove(smEdizUnitVar);
		smEdizUnitVar.setSmVariable(null);

		return smEdizUnitVar;
	}

	 
	public SmInstanceVariable addSmInstanceVariable(SmInstanceVariable smInstanceVariable) {
		getSmInstanceVariables().add(smInstanceVariable);
		smInstanceVariable.setSmVariable(this);

		return smInstanceVariable;
	}

	public SmInstanceVariable removeSmInstanceVariable(SmInstanceVariable smInstanceVariable) {
		getSmInstanceVariables().remove(smInstanceVariable);
		smInstanceVariable.setSmVariable(null);

		return smInstanceVariable;
	}

 

}