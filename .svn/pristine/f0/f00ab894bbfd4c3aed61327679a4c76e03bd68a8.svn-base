package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SM_UNIT_EDITION_FASE database table.
 * 
 */
@Entity
@Table(name="SM_UNIT_EDITION_FASE")
@NamedQuery(name="SmUnitEditionFase.findAll", query="SELECT s FROM SmUnitEditionFase s")
public class SmUnitEditionFase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=100)
	private String descr;

	@Column(length=50)
	private String nome;

	//bi-directional many-to-one association to SmEdizione
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EDIZIONE")
	private SmEdizione smEdizione;

	//bi-directional many-to-one association to SmFase
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FASE")
	private SmFase smFase;

	//bi-directional many-to-one association to SmUnit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UNIT")
	private SmUnit smUnit;

	public SmUnitEditionFase() {
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

	public SmEdizione getSmEdizione() {
		return this.smEdizione;
	}

	public void setSmEdizione(SmEdizione smEdizione) {
		this.smEdizione = smEdizione;
	}

	public SmFase getSmFase() {
		return this.smFase;
	}

	public void setSmFase(SmFase smFase) {
		this.smFase = smFase;
	}

	public SmUnit getSmUnit() {
		return this.smUnit;
	}

	public void setSmUnit(SmUnit smUnit) {
		this.smUnit = smUnit;
	}

}