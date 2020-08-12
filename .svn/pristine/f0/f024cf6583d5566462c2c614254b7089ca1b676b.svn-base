package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SM_POPULATION database table.
 * 
 */
@Entity
@Table(name="SM_POPULATION")
@NamedQuery(name="SmPopulation.findAll", query="SELECT s FROM SmPopulation s")
public class SmPopulation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=100)
	private String descrizione;

	//bi-directional many-to-one association to SmEdizione
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EDIZIONE", nullable=false)
	private SmEdizione smEdizione;

	public SmPopulation() {
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

	public SmEdizione getSmEdizione() {
		return this.smEdizione;
	}

	public void setSmEdizione(SmEdizione smEdizione) {
		this.smEdizione = smEdizione;
	}

}