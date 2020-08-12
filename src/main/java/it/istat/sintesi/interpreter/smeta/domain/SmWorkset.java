package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.math.BigDecimal;


/**
 * The persistent class for the SM_WORKSET database table.
 * 
 */
@Data
@Entity
@Table(name="SM_WORKSET")
@NamedQuery(name="SmWorkset.findAll", query="SELECT s FROM SmWorkset s")
public class SmWorkset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=255)
	private String descrizione;

	@Column(name="ID_OGGETTO")
	private Long idOggetto;

	@Column(name="NOME_OGGETTO", length=16)
	private String nomeOggetto;

	@Column(nullable=false)
	private Short ordine;

	@Column(name="TIPO_IO")
	private Short tipoIO;

	//bi-directional many-to-one association to SmStepInstance
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ISTANZA")
	private SmStepInstance smStepInstance;

	//bi-directional many-to-one association to SmTipoOggetto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TIPO_OGGETTO")
	private SmTipoOggetto smTipoOggetto;

	public SmWorkset() {
	}



}