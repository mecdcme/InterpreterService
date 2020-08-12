package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.math.BigDecimal;


/**
 * The persistent class for the SM_EDIZ_UNIT_VAR_MODELDATA database table.
 * 
 */
@Data
@Entity
@Table(name="SM_EDIZ_UNIT_VAR_MODELDATA")
@NamedQuery(name="SmEdizUnitVarModeldata.findAll", query="SELECT s FROM SmEdizUnitVarModeldata s")
public class SmEdizUnitVarModeldata implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SM_EDIZ_UV_ID", unique=true, nullable=false)
	private long smEdizUvId;

	@Column(length=300)
	private String condizione;

	@Column(name="FLAG_ATTIVO", precision=1)
	private Short flagAttivo;

	@Column(name="FLAG_CHIAVE", precision=1)
	private Short flagChiave;

	@Column(name="FLAG_CHIAVE_EXT", precision=1)
	private Short flagChiaveExt;

	@Column(name="FLAG_CHIAVE_INT", precision=1)
	private Short flagChiaveInt;

	@Column(name="FLAG_FISSO", precision=1)
	private Short flagFisso;

	@Column(name="FLAG_LISTA", precision=1)
	private Short flagLista;

	@Column(name="FLAG_OGGETTO", precision=1)
	private Short flagOggetto;

	@Column(name="FLAG_SORT", precision=1)
	private Short flagSort;

	//bi-directional one-to-one association to SmEdizUnitVar
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SM_EDIZ_UV_ID", nullable=false, insertable=false, updatable=false)
	private SmEdizUnitVar smEdizUnitVar;

	public SmEdizUnitVarModeldata() {
	}

	 
}