package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SM_INDAGINE_PSN database table.
 * 
 */
@Entity
@Table(name="SM_INDAGINE_PSN")
@NamedQuery(name="SmIndaginePsn.findAll", query="SELECT s FROM SmIndaginePsn s")
public class SmIndaginePsn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="ANNO_RIF_DA", nullable=false, precision=4)
	private BigDecimal annoRifDa;

	@Column(name="CODICE_PROCESSO_SIDI", nullable=false, length=21)
	private String codiceProcessoSidi;

	@Column(name="CODICE_PSN", length=147)
	private String codicePsn;

	//bi-directional many-to-one association to SmIndagine
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INDAGINE")
	private SmIndagine smIndagine;

	public SmIndaginePsn() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAnnoRifDa() {
		return this.annoRifDa;
	}

	public void setAnnoRifDa(BigDecimal annoRifDa) {
		this.annoRifDa = annoRifDa;
	}

	public String getCodiceProcessoSidi() {
		return this.codiceProcessoSidi;
	}

	public void setCodiceProcessoSidi(String codiceProcessoSidi) {
		this.codiceProcessoSidi = codiceProcessoSidi;
	}

	public String getCodicePsn() {
		return this.codicePsn;
	}

	public void setCodicePsn(String codicePsn) {
		this.codicePsn = codicePsn;
	}

	public SmIndagine getSmIndagine() {
		return this.smIndagine;
	}

	public void setSmIndagine(SmIndagine smIndagine) {
		this.smIndagine = smIndagine;
	}

}