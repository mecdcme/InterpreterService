package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SM_PERIODICITA database table.
 * 
 */
@Entity
@Table(name="SM_PERIODICITA")
@NamedQuery(name="SmPeriodicita.findAll", query="SELECT s FROM SmPeriodicita s")
public class SmPeriodicita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=50)
	private String descrizione;

	//bi-directional many-to-one association to SmEdizione
	@OneToMany(mappedBy="smPeriodicita")
	private List<SmEdizione> smEdiziones;

	public SmPeriodicita() {
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

	public List<SmEdizione> getSmEdiziones() {
		return this.smEdiziones;
	}

	public void setSmEdiziones(List<SmEdizione> smEdiziones) {
		this.smEdiziones = smEdiziones;
	}

	public SmEdizione addSmEdizione(SmEdizione smEdizione) {
		getSmEdiziones().add(smEdizione);
		smEdizione.setSmPeriodicita(this);

		return smEdizione;
	}

	public SmEdizione removeSmEdizione(SmEdizione smEdizione) {
		getSmEdiziones().remove(smEdizione);
		smEdizione.setSmPeriodicita(null);

		return smEdizione;
	}

}