package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SM_INDAGINE_FASI_SIDI database table.
 * 
 */
@Entity
@Table(name="SM_INDAGINE_FASI_SIDI")
@NamedQuery(name="SmIndagineFasiSidi.findAll", query="SELECT s FROM SmIndagineFasiSidi s")
public class SmIndagineFasiSidi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="CODICE_FASE", nullable=false, precision=10)
	private BigDecimal codiceFase;

	@Column(name="CODICE_FASE_PADRE", precision=10)
	private BigDecimal codiceFasePadre;

	@Column(name="CODICE_PROCESSO_SIDI", nullable=false, length=21)
	private String codiceProcessoSidi;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FIN")
	private Date dataFin;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_IN")
	private Date dataIn;

	@Column(name="DESCRIZIONE_FASE", length=4000)
	private String descrizioneFase;

	@Column(name="DESCRIZIONE_FASE_ENG", length=4000)
	private String descrizioneFaseEng;

	@Column(name="NOME_FASE", nullable=false, length=450)
	private String nomeFase;

	@Column(name="NOME_FASE_ENG", length=450)
	private String nomeFaseEng;

	@Column(precision=10)
	private BigDecimal ordine0;

	@Column(precision=10)
	private BigDecimal ordine1;

	@Column(precision=10)
	private BigDecimal ordine2;

	@Column(precision=10)
	private BigDecimal ordine3;

	@Column(name="STATO_FASE", length=39)
	private String statoFase;

	//bi-directional many-to-one association to SmIndagine
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INDAGINE")
	private SmIndagine smIndagine;

	public SmIndagineFasiSidi() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getCodiceFase() {
		return this.codiceFase;
	}

	public void setCodiceFase(BigDecimal codiceFase) {
		this.codiceFase = codiceFase;
	}

	public BigDecimal getCodiceFasePadre() {
		return this.codiceFasePadre;
	}

	public void setCodiceFasePadre(BigDecimal codiceFasePadre) {
		this.codiceFasePadre = codiceFasePadre;
	}

	public String getCodiceProcessoSidi() {
		return this.codiceProcessoSidi;
	}

	public void setCodiceProcessoSidi(String codiceProcessoSidi) {
		this.codiceProcessoSidi = codiceProcessoSidi;
	}

	public Date getDataFin() {
		return this.dataFin;
	}

	public void setDataFin(Date dataFin) {
		this.dataFin = dataFin;
	}

	public Date getDataIn() {
		return this.dataIn;
	}

	public void setDataIn(Date dataIn) {
		this.dataIn = dataIn;
	}

	public String getDescrizioneFase() {
		return this.descrizioneFase;
	}

	public void setDescrizioneFase(String descrizioneFase) {
		this.descrizioneFase = descrizioneFase;
	}

	public String getDescrizioneFaseEng() {
		return this.descrizioneFaseEng;
	}

	public void setDescrizioneFaseEng(String descrizioneFaseEng) {
		this.descrizioneFaseEng = descrizioneFaseEng;
	}

	public String getNomeFase() {
		return this.nomeFase;
	}

	public void setNomeFase(String nomeFase) {
		this.nomeFase = nomeFase;
	}

	public String getNomeFaseEng() {
		return this.nomeFaseEng;
	}

	public void setNomeFaseEng(String nomeFaseEng) {
		this.nomeFaseEng = nomeFaseEng;
	}

	public BigDecimal getOrdine0() {
		return this.ordine0;
	}

	public void setOrdine0(BigDecimal ordine0) {
		this.ordine0 = ordine0;
	}

	public BigDecimal getOrdine1() {
		return this.ordine1;
	}

	public void setOrdine1(BigDecimal ordine1) {
		this.ordine1 = ordine1;
	}

	public BigDecimal getOrdine2() {
		return this.ordine2;
	}

	public void setOrdine2(BigDecimal ordine2) {
		this.ordine2 = ordine2;
	}

	public BigDecimal getOrdine3() {
		return this.ordine3;
	}

	public void setOrdine3(BigDecimal ordine3) {
		this.ordine3 = ordine3;
	}

	public String getStatoFase() {
		return this.statoFase;
	}

	public void setStatoFase(String statoFase) {
		this.statoFase = statoFase;
	}

	public SmIndagine getSmIndagine() {
		return this.smIndagine;
	}

	public void setSmIndagine(SmIndagine smIndagine) {
		this.smIndagine = smIndagine;
	}

}