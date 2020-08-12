package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.math.BigDecimal;


/**
 * The persistent class for the SM_EDIZ_UNIT_VAR database table.
 * 
 */
@Data
@Entity
@Table(name="SM_EDIZ_UNIT_VAR")
@NamedQuery(name="SmEdizUnitVar.findAll", query="SELECT s FROM SmEdizUnitVar s")
public class SmEdizUnitVar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(precision=3)
	private Short ordine;
	
	@Column
	private Short attiva;
	
	@Column(name="ALIAS_SQL")
	private String  aliasSql;

	//bi-directional many-to-one association to SmUnit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UNIT")
	private SmUnit smUnit;

	//bi-directional many-to-one association to SmVariable
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "VARIABILE", referencedColumnName = "ID")
	private SmVariable smVariable;

	//bi-directional one-to-one association to SmEdizUnitVarModeldata
	@OneToOne(mappedBy="smEdizUnitVar", fetch=FetchType.LAZY)
	private SmEdizUnitVarModeldata smEdizUnitVarModeldata;

	public SmEdizUnitVar() {
	}

	
}