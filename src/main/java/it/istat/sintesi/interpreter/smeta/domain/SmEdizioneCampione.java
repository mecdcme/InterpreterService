package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SM_EDIZIONE_CAMPIONE database table.
 * 
 */
@Entity
@Table(name="SM_EDIZIONE_CAMPIONE")
@NamedQuery(name="SmEdizioneCampione.findAll", query="SELECT s FROM SmEdizioneCampione s")
public class SmEdizioneCampione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(nullable=false)
	private BigDecimal edizione;

	@Column(length=100)
	private String tabella;

	//bi-directional many-to-one association to SmCampione
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CAMPIONE", nullable=false)
	private SmCampione smCampione;

	public SmEdizioneCampione() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getEdizione() {
		return this.edizione;
	}

	public void setEdizione(BigDecimal edizione) {
		this.edizione = edizione;
	}

	public String getTabella() {
		return this.tabella;
	}

	public void setTabella(String tabella) {
		this.tabella = tabella;
	}

	public SmCampione getSmCampione() {
		return this.smCampione;
	}

	public void setSmCampione(SmCampione smCampione) {
		this.smCampione = smCampione;
	}

}