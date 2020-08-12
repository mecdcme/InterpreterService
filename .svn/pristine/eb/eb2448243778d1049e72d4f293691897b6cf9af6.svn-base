package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SM_INDAGINE_LISTA_UTENTI database table.
 * 
 */
@Entity
@Table(name="SM_INDAGINE_LISTA_UTENTI")
@NamedQuery(name="SmIndagineListaUtenti.findAll", query="SELECT s FROM SmIndagineListaUtenti s")
public class SmIndagineListaUtenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=50)
	private String tabella;

	//bi-directional many-to-one association to SmIndagine
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INDAGINE")
	private SmIndagine smIndagine;

	public SmIndagineListaUtenti() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTabella() {
		return this.tabella;
	}

	public void setTabella(String tabella) {
		this.tabella = tabella;
	}

	public SmIndagine getSmIndagine() {
		return this.smIndagine;
	}

	public void setSmIndagine(SmIndagine smIndagine) {
		this.smIndagine = smIndagine;
	}

}