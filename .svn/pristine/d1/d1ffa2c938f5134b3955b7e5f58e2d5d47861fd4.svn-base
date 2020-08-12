package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the SM_VARIABILE_TYPE database table.
 * 
 */
@Entity
@Table(name="SM_VARIABILE_TYPE")
@NamedQuery(name="SmVariabileType.findAll", query="SELECT s FROM SmVariabileType s")
public class SmVariabileType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="NOME_TIPO_VARIABILE_ENG", length=765)
	private String nomeTipoVariabileEng;

	@Column(name="NOME_TIPO_VARIABILE_ITA", length=765)
	private String nomeTipoVariabileIta;

	@Column(precision=3)
	private BigDecimal ordine;

	@Column(name="TIPO_VARIABILE", nullable=false, length=6)
	private String tipoVariabile;

	@Column(length=600)
	private String titolo;

	//bi-directional many-to-one association to SmVariable
	@OneToMany(mappedBy="smVariabileType")
	private List<SmVariable> smVariables;

	public SmVariabileType() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeTipoVariabileEng() {
		return this.nomeTipoVariabileEng;
	}

	public void setNomeTipoVariabileEng(String nomeTipoVariabileEng) {
		this.nomeTipoVariabileEng = nomeTipoVariabileEng;
	}

	public String getNomeTipoVariabileIta() {
		return this.nomeTipoVariabileIta;
	}

	public void setNomeTipoVariabileIta(String nomeTipoVariabileIta) {
		this.nomeTipoVariabileIta = nomeTipoVariabileIta;
	}

	public BigDecimal getOrdine() {
		return this.ordine;
	}

	public void setOrdine(BigDecimal ordine) {
		this.ordine = ordine;
	}

	public String getTipoVariabile() {
		return this.tipoVariabile;
	}

	public void setTipoVariabile(String tipoVariabile) {
		this.tipoVariabile = tipoVariabile;
	}

	public String getTitolo() {
		return this.titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public List<SmVariable> getSmVariables() {
		return this.smVariables;
	}

	public void setSmVariables(List<SmVariable> smVariables) {
		this.smVariables = smVariables;
	}

	public SmVariable addSmVariable(SmVariable smVariable) {
		getSmVariables().add(smVariable);
		smVariable.setSmVariabileType(this);

		return smVariable;
	}

	public SmVariable removeSmVariable(SmVariable smVariable) {
		getSmVariables().remove(smVariable);
		smVariable.setSmVariabileType(null);

		return smVariable;
	}

}