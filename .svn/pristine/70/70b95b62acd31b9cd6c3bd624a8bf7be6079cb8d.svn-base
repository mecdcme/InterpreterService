package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SM_EDIZIONE_TIPO database table.
 * 
 */
@Entity
@Table(name="SM_EDIZIONE_TIPO")
@NamedQuery(name="SmEdizioneTipo.findAll", query="SELECT s FROM SmEdizioneTipo s")
public class SmEdizioneTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=50)
	private String descrizione;

	//bi-directional many-to-one association to SmEdizione
	@OneToMany(mappedBy="smEdizioneTipo")
	private List<SmEdizione> smEdiziones;

	public SmEdizioneTipo() {
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
		smEdizione.setSmEdizioneTipo(this);

		return smEdizione;
	}

	public SmEdizione removeSmEdizione(SmEdizione smEdizione) {
		getSmEdiziones().remove(smEdizione);
		smEdizione.setSmEdizioneTipo(null);

		return smEdizione;
	}

}