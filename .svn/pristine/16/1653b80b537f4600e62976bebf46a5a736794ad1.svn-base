package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SM_INDAGINE_FENOMENI database table.
 * 
 */
@Entity
@Table(name="SM_INDAGINE_FENOMENI")
@NamedQuery(name="SmIndagineFenomeni.findAll", query="SELECT s FROM SmIndagineFenomeni s")
public class SmIndagineFenomeni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="CODICE_FENOMENO", nullable=false, precision=10)
	private BigDecimal codiceFenomeno;

	@Column(name="CODICE_PROCESSO_SIDI", nullable=false, length=21)
	private String codiceProcessoSidi;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FIN", nullable=false)
	private Date dataFin;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_IN", nullable=false)
	private Date dataIn;

	@Column(name="DESCRIZIONE_FEN", length=4000)
	private String descrizioneFen;

	@Column(name="DESCRIZIONE_FEN_ENG", length=4000)
	private String descrizioneFenEng;

	@Column(name="NOME_FENOMENO", nullable=false, length=450)
	private String nomeFenomeno;

	@Column(name="NOME_FENOMENO_ENG", length=450)
	private String nomeFenomenoEng;

	@Column(name="STATO_FEN", length=39)
	private String statoFen;

	//bi-directional many-to-one association to SmIndagine
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INDAGINE")
	private SmIndagine smIndagine;

	public SmIndagineFenomeni() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getCodiceFenomeno() {
		return this.codiceFenomeno;
	}

	public void setCodiceFenomeno(BigDecimal codiceFenomeno) {
		this.codiceFenomeno = codiceFenomeno;
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

	public String getDescrizioneFen() {
		return this.descrizioneFen;
	}

	public void setDescrizioneFen(String descrizioneFen) {
		this.descrizioneFen = descrizioneFen;
	}

	public String getDescrizioneFenEng() {
		return this.descrizioneFenEng;
	}

	public void setDescrizioneFenEng(String descrizioneFenEng) {
		this.descrizioneFenEng = descrizioneFenEng;
	}

	public String getNomeFenomeno() {
		return this.nomeFenomeno;
	}

	public void setNomeFenomeno(String nomeFenomeno) {
		this.nomeFenomeno = nomeFenomeno;
	}

	public String getNomeFenomenoEng() {
		return this.nomeFenomenoEng;
	}

	public void setNomeFenomenoEng(String nomeFenomenoEng) {
		this.nomeFenomenoEng = nomeFenomenoEng;
	}

	public String getStatoFen() {
		return this.statoFen;
	}

	public void setStatoFen(String statoFen) {
		this.statoFen = statoFen;
	}

	public SmIndagine getSmIndagine() {
		return this.smIndagine;
	}

	public void setSmIndagine(SmIndagine smIndagine) {
		this.smIndagine = smIndagine;
	}

}