package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SM_CAMPIONE database table.
 * 
 */
@Entity
@Table(name="SM_CAMPIONE")
@NamedQuery(name="SmCampione.findAll", query="SELECT s FROM SmCampione s")
public class SmCampione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=100)
	private String descrizione;

	//bi-directional many-to-one association to SmEdizioneCampione
	@OneToMany(mappedBy="smCampione")
	private List<SmEdizioneCampione> smEdizioneCampiones;

	public SmCampione() {
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

	public List<SmEdizioneCampione> getSmEdizioneCampiones() {
		return this.smEdizioneCampiones;
	}

	public void setSmEdizioneCampiones(List<SmEdizioneCampione> smEdizioneCampiones) {
		this.smEdizioneCampiones = smEdizioneCampiones;
	}

	public SmEdizioneCampione addSmEdizioneCampione(SmEdizioneCampione smEdizioneCampione) {
		getSmEdizioneCampiones().add(smEdizioneCampione);
		smEdizioneCampione.setSmCampione(this);

		return smEdizioneCampione;
	}

	public SmEdizioneCampione removeSmEdizioneCampione(SmEdizioneCampione smEdizioneCampione) {
		getSmEdizioneCampiones().remove(smEdizioneCampione);
		smEdizioneCampione.setSmCampione(null);

		return smEdizioneCampione;
	}

}