package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SM_INSTANCE_VARIABLE database table.
 * 
 */
@Entity
@Table(name="SM_INSTANCE_VARIABLE")
@NamedQuery(name="SmInstanceVariable.findAll", query="SELECT s FROM SmInstanceVariable s")
public class SmInstanceVariable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, precision=20)
	private long id;

	@Column(length=100)
	private String descrizione;

	@Column(length=50)
	private String etichetta;

	@Column(length=10)
	private String indagine;

	@Column(length=30)
	private String inf;

	@Column(name="\"LENGTH\"", precision=10)
	private BigDecimal length;

	@Column(length=30)
	private String miss;

	@Column(name="NOME_CAMPO", nullable=false, length=50)
	private String nomeCampo;

	@Column(length=30)
	private String sup;

	@Column(length=30)
	private String tipo;

	@Column(name="TIPO_VARIABILE_SUM", precision=2)
	private BigDecimal tipoVariabileSum;

	//bi-directional many-to-one association to SmVariable
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_VARIABLE")
	private SmVariable smVariable;

	public SmInstanceVariable() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getEtichetta() {
		return this.etichetta;
	}

	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
	}

	public String getIndagine() {
		return this.indagine;
	}

	public void setIndagine(String indagine) {
		this.indagine = indagine;
	}

	public String getInf() {
		return this.inf;
	}

	public void setInf(String inf) {
		this.inf = inf;
	}

	public BigDecimal getLength() {
		return this.length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public String getMiss() {
		return this.miss;
	}

	public void setMiss(String miss) {
		this.miss = miss;
	}

	public String getNomeCampo() {
		return this.nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getSup() {
		return this.sup;
	}

	public void setSup(String sup) {
		this.sup = sup;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getTipoVariabileSum() {
		return this.tipoVariabileSum;
	}

	public void setTipoVariabileSum(BigDecimal tipoVariabileSum) {
		this.tipoVariabileSum = tipoVariabileSum;
	}

	public SmVariable getSmVariable() {
		return this.smVariable;
	}

	public void setSmVariable(SmVariable smVariable) {
		this.smVariable = smVariable;
	}

}