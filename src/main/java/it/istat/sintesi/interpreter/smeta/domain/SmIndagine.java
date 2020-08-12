package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the SM_INDAGINE database table.
 * 
 */
@Entity
@Table(name="SM_INDAGINE")
@NamedQuery(name="SmIndagine.findAll", query="SELECT s FROM SmIndagine s")
public class SmIndagine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="CODICE_DISEGNO", precision=5)
	private BigDecimal codiceDisegno;

	@Column(name="CODICE_PROCESSO_SIDI", nullable=false, length=21)
	private String codiceProcessoSidi;

	@Column(length=4000)
	private String descrizione;

	@Column(name="DESCRIZIONE_ENG", length=4000)
	private String descrizioneEng;

	@Column(name="EMAIL_RESPONSABILE", length=327)
	private String emailResponsabile;

	@Column(name="NOME_DISEGNO", length=450)
	private String nomeDisegno;

	@Column(name="NOME_DISEGNO_ENG", length=450)
	private String nomeDisegnoEng;

	@Column(name="NOME_RESPONSABILE", length=603)
	private String nomeResponsabile;

	@Column(name="NOME_SIDI", nullable=false, length=450)
	private String nomeSidi;

	@Column(name="NOME_SIDI_ENG", length=450)
	private String nomeSidiEng;

	@Column(name="NOME_TIPO_PROCESSO", nullable=false, length=450)
	private String nomeTipoProcesso;

	@Column(length=4000)
	private String normativa;

	@Column(name="PERIOD_CICLO_PROD", length=90)
	private String periodCicloProd;

	@Column(name="PERIOD_CICLO_PROD_ENG", length=90)
	private String periodCicloProdEng;

	@Column(length=150)
	private String sigla;

	@Column(length=21)
	private String stato;

	@Column(name="TIPO_PROCESSO", precision=1)
	private BigDecimal tipoProcesso;

	//bi-directional many-to-one association to SmEdizione
	@OneToMany(mappedBy="smIndagine")
	private List<SmEdizione> smEdiziones;

	//bi-directional many-to-one association to SmIndagineFasiSidi
	@OneToMany(mappedBy="smIndagine")
	private List<SmIndagineFasiSidi> smIndagineFasiSidis;

	//bi-directional many-to-one association to SmIndagineFenomeni
	@OneToMany(mappedBy="smIndagine")
	private List<SmIndagineFenomeni> smIndagineFenomenis;

	//bi-directional many-to-one association to SmIndagineListaUtenti
	@OneToMany(mappedBy="smIndagine")
	private List<SmIndagineListaUtenti> smIndagineListaUtentis;

	//bi-directional many-to-one association to SmIndaginePsn
	@OneToMany(mappedBy="smIndagine")
	private List<SmIndaginePsn> smIndaginePsns;

	//bi-directional many-to-one association to SmIndagineUnitaRil
	@OneToMany(mappedBy="smIndagine")
	private List<SmIndagineUnitaRil> smIndagineUnitaRils;

	public SmIndagine() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getCodiceDisegno() {
		return this.codiceDisegno;
	}

	public void setCodiceDisegno(BigDecimal codiceDisegno) {
		this.codiceDisegno = codiceDisegno;
	}

	public String getCodiceProcessoSidi() {
		return this.codiceProcessoSidi;
	}

	public void setCodiceProcessoSidi(String codiceProcessoSidi) {
		this.codiceProcessoSidi = codiceProcessoSidi;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizioneEng() {
		return this.descrizioneEng;
	}

	public void setDescrizioneEng(String descrizioneEng) {
		this.descrizioneEng = descrizioneEng;
	}

	public String getEmailResponsabile() {
		return this.emailResponsabile;
	}

	public void setEmailResponsabile(String emailResponsabile) {
		this.emailResponsabile = emailResponsabile;
	}

	public String getNomeDisegno() {
		return this.nomeDisegno;
	}

	public void setNomeDisegno(String nomeDisegno) {
		this.nomeDisegno = nomeDisegno;
	}

	public String getNomeDisegnoEng() {
		return this.nomeDisegnoEng;
	}

	public void setNomeDisegnoEng(String nomeDisegnoEng) {
		this.nomeDisegnoEng = nomeDisegnoEng;
	}

	public String getNomeResponsabile() {
		return this.nomeResponsabile;
	}

	public void setNomeResponsabile(String nomeResponsabile) {
		this.nomeResponsabile = nomeResponsabile;
	}

	public String getNomeSidi() {
		return this.nomeSidi;
	}

	public void setNomeSidi(String nomeSidi) {
		this.nomeSidi = nomeSidi;
	}

	public String getNomeSidiEng() {
		return this.nomeSidiEng;
	}

	public void setNomeSidiEng(String nomeSidiEng) {
		this.nomeSidiEng = nomeSidiEng;
	}

	public String getNomeTipoProcesso() {
		return this.nomeTipoProcesso;
	}

	public void setNomeTipoProcesso(String nomeTipoProcesso) {
		this.nomeTipoProcesso = nomeTipoProcesso;
	}

	public String getNormativa() {
		return this.normativa;
	}

	public void setNormativa(String normativa) {
		this.normativa = normativa;
	}

	public String getPeriodCicloProd() {
		return this.periodCicloProd;
	}

	public void setPeriodCicloProd(String periodCicloProd) {
		this.periodCicloProd = periodCicloProd;
	}

	public String getPeriodCicloProdEng() {
		return this.periodCicloProdEng;
	}

	public void setPeriodCicloProdEng(String periodCicloProdEng) {
		this.periodCicloProdEng = periodCicloProdEng;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public BigDecimal getTipoProcesso() {
		return this.tipoProcesso;
	}

	public void setTipoProcesso(BigDecimal tipoProcesso) {
		this.tipoProcesso = tipoProcesso;
	}

	public List<SmEdizione> getSmEdiziones() {
		return this.smEdiziones;
	}

	public void setSmEdiziones(List<SmEdizione> smEdiziones) {
		this.smEdiziones = smEdiziones;
	}

	public SmEdizione addSmEdizione(SmEdizione smEdizione) {
		getSmEdiziones().add(smEdizione);
		smEdizione.setSmIndagine(this);

		return smEdizione;
	}

	public SmEdizione removeSmEdizione(SmEdizione smEdizione) {
		getSmEdiziones().remove(smEdizione);
		smEdizione.setSmIndagine(null);

		return smEdizione;
	}

	public List<SmIndagineFasiSidi> getSmIndagineFasiSidis() {
		return this.smIndagineFasiSidis;
	}

	public void setSmIndagineFasiSidis(List<SmIndagineFasiSidi> smIndagineFasiSidis) {
		this.smIndagineFasiSidis = smIndagineFasiSidis;
	}

	public SmIndagineFasiSidi addSmIndagineFasiSidi(SmIndagineFasiSidi smIndagineFasiSidi) {
		getSmIndagineFasiSidis().add(smIndagineFasiSidi);
		smIndagineFasiSidi.setSmIndagine(this);

		return smIndagineFasiSidi;
	}

	public SmIndagineFasiSidi removeSmIndagineFasiSidi(SmIndagineFasiSidi smIndagineFasiSidi) {
		getSmIndagineFasiSidis().remove(smIndagineFasiSidi);
		smIndagineFasiSidi.setSmIndagine(null);

		return smIndagineFasiSidi;
	}

	public List<SmIndagineFenomeni> getSmIndagineFenomenis() {
		return this.smIndagineFenomenis;
	}

	public void setSmIndagineFenomenis(List<SmIndagineFenomeni> smIndagineFenomenis) {
		this.smIndagineFenomenis = smIndagineFenomenis;
	}

	public SmIndagineFenomeni addSmIndagineFenomeni(SmIndagineFenomeni smIndagineFenomeni) {
		getSmIndagineFenomenis().add(smIndagineFenomeni);
		smIndagineFenomeni.setSmIndagine(this);

		return smIndagineFenomeni;
	}

	public SmIndagineFenomeni removeSmIndagineFenomeni(SmIndagineFenomeni smIndagineFenomeni) {
		getSmIndagineFenomenis().remove(smIndagineFenomeni);
		smIndagineFenomeni.setSmIndagine(null);

		return smIndagineFenomeni;
	}

	public List<SmIndagineListaUtenti> getSmIndagineListaUtentis() {
		return this.smIndagineListaUtentis;
	}

	public void setSmIndagineListaUtentis(List<SmIndagineListaUtenti> smIndagineListaUtentis) {
		this.smIndagineListaUtentis = smIndagineListaUtentis;
	}

	public SmIndagineListaUtenti addSmIndagineListaUtenti(SmIndagineListaUtenti smIndagineListaUtenti) {
		getSmIndagineListaUtentis().add(smIndagineListaUtenti);
		smIndagineListaUtenti.setSmIndagine(this);

		return smIndagineListaUtenti;
	}

	public SmIndagineListaUtenti removeSmIndagineListaUtenti(SmIndagineListaUtenti smIndagineListaUtenti) {
		getSmIndagineListaUtentis().remove(smIndagineListaUtenti);
		smIndagineListaUtenti.setSmIndagine(null);

		return smIndagineListaUtenti;
	}

	public List<SmIndaginePsn> getSmIndaginePsns() {
		return this.smIndaginePsns;
	}

	public void setSmIndaginePsns(List<SmIndaginePsn> smIndaginePsns) {
		this.smIndaginePsns = smIndaginePsns;
	}

	public SmIndaginePsn addSmIndaginePsn(SmIndaginePsn smIndaginePsn) {
		getSmIndaginePsns().add(smIndaginePsn);
		smIndaginePsn.setSmIndagine(this);

		return smIndaginePsn;
	}

	public SmIndaginePsn removeSmIndaginePsn(SmIndaginePsn smIndaginePsn) {
		getSmIndaginePsns().remove(smIndaginePsn);
		smIndaginePsn.setSmIndagine(null);

		return smIndaginePsn;
	}

	public List<SmIndagineUnitaRil> getSmIndagineUnitaRils() {
		return this.smIndagineUnitaRils;
	}

	public void setSmIndagineUnitaRils(List<SmIndagineUnitaRil> smIndagineUnitaRils) {
		this.smIndagineUnitaRils = smIndagineUnitaRils;
	}

	public SmIndagineUnitaRil addSmIndagineUnitaRil(SmIndagineUnitaRil smIndagineUnitaRil) {
		getSmIndagineUnitaRils().add(smIndagineUnitaRil);
		smIndagineUnitaRil.setSmIndagine(this);

		return smIndagineUnitaRil;
	}

	public SmIndagineUnitaRil removeSmIndagineUnitaRil(SmIndagineUnitaRil smIndagineUnitaRil) {
		getSmIndagineUnitaRils().remove(smIndagineUnitaRil);
		smIndagineUnitaRil.setSmIndagine(null);

		return smIndagineUnitaRil;
	}

}