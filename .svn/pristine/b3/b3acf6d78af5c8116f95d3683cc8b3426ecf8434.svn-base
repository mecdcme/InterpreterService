package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SM_LINK_EDIZIONE_FUNCTION database table.
 * 
 */
@Data
@Entity
@Table(name="SM_LINK_EDIZIONE_FUNCTION")
@NamedQuery(name="SmLinkEdizioneFunction.findAll", query="SELECT s FROM SmLinkEdizioneFunction s")
public class SmLinkEdizioneFunction implements Serializable {
	private static final long serialVersionUID = 1L;

	private Short attiva;

	private Short errore;

	private Short eseguita;

	@Temporal(TemporalType.DATE)
	private Date quando;

	private BigDecimal utente;

	//bi-directional many-to-one association to SmBusinessFunction
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EDIZIONE")
	private SmBusinessFunction smBusinessFunction;

	//bi-directional many-to-one association to SmEdizione
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FUNZIONE")
	private SmEdizione smEdizione;

	public SmLinkEdizioneFunction() {
	}

	 
}