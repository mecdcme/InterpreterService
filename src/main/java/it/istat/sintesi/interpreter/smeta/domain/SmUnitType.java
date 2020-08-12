package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SM_UNIT_TYPE database table.
 * 
 */
@Entity
@Table(name="SM_UNIT_TYPE")
@NamedQuery(name="SmUnitType.findAll", query="SELECT s FROM SmUnitType s")
public class SmUnitType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="DESCRIZIONE_EN", length=900)
	private String descrizioneEn;

	@Column(name="DESCRIZIONE_IT", nullable=false, length=900)
	private String descrizioneIt;

	@Column(name="ID_UNIT_TYPE", nullable=false, length=150)
	private String idUnitType;

	//bi-directional many-to-one association to SmIndagineUnitaRil
	@OneToMany(mappedBy="smUnitType")
	private List<SmIndagineUnitaRil> smIndagineUnitaRils;

	//bi-directional many-to-one association to SmUnit
	@OneToMany(mappedBy="smUnitType")
	private List<SmUnit> smUnits;

	public SmUnitType() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescrizioneEn() {
		return this.descrizioneEn;
	}

	public void setDescrizioneEn(String descrizioneEn) {
		this.descrizioneEn = descrizioneEn;
	}

	public String getDescrizioneIt() {
		return this.descrizioneIt;
	}

	public void setDescrizioneIt(String descrizioneIt) {
		this.descrizioneIt = descrizioneIt;
	}

	public String getIdUnitType() {
		return this.idUnitType;
	}

	public void setIdUnitType(String idUnitType) {
		this.idUnitType = idUnitType;
	}

	public List<SmIndagineUnitaRil> getSmIndagineUnitaRils() {
		return this.smIndagineUnitaRils;
	}

	public void setSmIndagineUnitaRils(List<SmIndagineUnitaRil> smIndagineUnitaRils) {
		this.smIndagineUnitaRils = smIndagineUnitaRils;
	}

	public SmIndagineUnitaRil addSmIndagineUnitaRil(SmIndagineUnitaRil smIndagineUnitaRil) {
		getSmIndagineUnitaRils().add(smIndagineUnitaRil);
		smIndagineUnitaRil.setSmUnitType(this);

		return smIndagineUnitaRil;
	}

	public SmIndagineUnitaRil removeSmIndagineUnitaRil(SmIndagineUnitaRil smIndagineUnitaRil) {
		getSmIndagineUnitaRils().remove(smIndagineUnitaRil);
		smIndagineUnitaRil.setSmUnitType(null);

		return smIndagineUnitaRil;
	}

	public List<SmUnit> getSmUnits() {
		return this.smUnits;
	}

	public void setSmUnits(List<SmUnit> smUnits) {
		this.smUnits = smUnits;
	}

	public SmUnit addSmUnit(SmUnit smUnit) {
		getSmUnits().add(smUnit);
		smUnit.setSmUnitType(this);

		return smUnit;
	}

	public SmUnit removeSmUnit(SmUnit smUnit) {
		getSmUnits().remove(smUnit);
		smUnit.setSmUnitType(null);

		return smUnit;
	}

}