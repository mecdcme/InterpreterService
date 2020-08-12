package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SM_INDAGINE_UNITA_RIL database table.
 * 
 */
@Entity
@Table(name="SM_INDAGINE_UNITA_RIL")
@NamedQuery(name="SmIndagineUnitaRil.findAll", query="SELECT s FROM SmIndagineUnitaRil s")
public class SmIndagineUnitaRil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="CODICE_PROCESSO_SIDI", nullable=false, length=21)
	private String codiceProcessoSidi;

	@Column(name="CODICE_UNITA", nullable=false, precision=10)
	private BigDecimal codiceUnita;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FIN")
	private Date dataFin;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_IN")
	private Date dataIn;

	@Column(name="DESCRIZIONE_UNITA", length=4000)
	private String descrizioneUnita;

	@Column(name="DESCRIZIONE_UNITA_ENG", length=4000)
	private String descrizioneUnitaEng;

	@Column(name="NOME_UNITA", nullable=false, length=450)
	private String nomeUnita;

	@Column(name="NOME_UNITA_ENG", length=450)
	private String nomeUnitaEng;

	@Column(name="STATO_UNITA", length=39)
	private String statoUnita;

	//bi-directional many-to-one association to SmIndagine
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INDAGINE")
	private SmIndagine smIndagine;

	//bi-directional many-to-one association to SmUnitType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UNIT_TYPE")
	private SmUnitType smUnitType;

	public SmIndagineUnitaRil() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodiceProcessoSidi() {
		return this.codiceProcessoSidi;
	}

	public void setCodiceProcessoSidi(String codiceProcessoSidi) {
		this.codiceProcessoSidi = codiceProcessoSidi;
	}

	public BigDecimal getCodiceUnita() {
		return this.codiceUnita;
	}

	public void setCodiceUnita(BigDecimal codiceUnita) {
		this.codiceUnita = codiceUnita;
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

	public String getDescrizioneUnita() {
		return this.descrizioneUnita;
	}

	public void setDescrizioneUnita(String descrizioneUnita) {
		this.descrizioneUnita = descrizioneUnita;
	}

	public String getDescrizioneUnitaEng() {
		return this.descrizioneUnitaEng;
	}

	public void setDescrizioneUnitaEng(String descrizioneUnitaEng) {
		this.descrizioneUnitaEng = descrizioneUnitaEng;
	}

	public String getNomeUnita() {
		return this.nomeUnita;
	}

	public void setNomeUnita(String nomeUnita) {
		this.nomeUnita = nomeUnita;
	}

	public String getNomeUnitaEng() {
		return this.nomeUnitaEng;
	}

	public void setNomeUnitaEng(String nomeUnitaEng) {
		this.nomeUnitaEng = nomeUnitaEng;
	}

	public String getStatoUnita() {
		return this.statoUnita;
	}

	public void setStatoUnita(String statoUnita) {
		this.statoUnita = statoUnita;
	}

	public SmIndagine getSmIndagine() {
		return this.smIndagine;
	}

	public void setSmIndagine(SmIndagine smIndagine) {
		this.smIndagine = smIndagine;
	}

	public SmUnitType getSmUnitType() {
		return this.smUnitType;
	}

	public void setSmUnitType(SmUnitType smUnitType) {
		this.smUnitType = smUnitType;
	}

}