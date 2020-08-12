package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the SM_EDIZIONE database table.
 * 
 */
@Data
@Entity
@Table(name = "SM_EDIZIONE")
@NamedQuery(name = "SmEdizione.findAll", query = "SELECT s FROM SmEdizione s")
public class SmEdizione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name = "CODICE_EDIZIONE", nullable = false, length = 50)
	private String codiceEdizione;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FIN")
	private Date dataFin;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_IN")
	private Date dataIn;

	@Column(name = "NOME_EDIZIONE", length = 100)
	private String nomeEdizione;

	// bi-directional many-to-one association to SmEdizioneTipo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TIPO")
	private SmEdizioneTipo smEdizioneTipo;

	// bi-directional many-to-one association to SmIndagine
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INDAGINE", nullable = false)
	private SmIndagine smIndagine;

	// bi-directional many-to-one association to SmPeriodicita
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERIODICITA")
	private SmPeriodicita smPeriodicita;

	// bi-directional many-to-one association to SmLinkEdizioneFunction
	@OneToMany(mappedBy = "smEdizione")
	private List<SmLinkEdizioneFunction> smLinkEdizioneFunctions;

	// bi-directional many-to-one association to SmPopulation
	@OneToMany(mappedBy = "smEdizione")
	private List<SmPopulation> smPopulations;

	// bi-directional many-to-one association to SmUnitEditionFase
	@OneToMany(mappedBy = "smEdizione")
	private List<SmUnitEditionFase> smUnitEditionFases;

	public SmEdizione() {
	}

	public SmLinkEdizioneFunction addSmLinkEdizioneFunction(SmLinkEdizioneFunction smLinkEdizioneFunction) {
		getSmLinkEdizioneFunctions().add(smLinkEdizioneFunction);
		smLinkEdizioneFunction.setSmEdizione(this);

		return smLinkEdizioneFunction;
	}

	public SmLinkEdizioneFunction removeSmLinkEdizioneFunction(SmLinkEdizioneFunction smLinkEdizioneFunction) {
		getSmLinkEdizioneFunctions().remove(smLinkEdizioneFunction);
		smLinkEdizioneFunction.setSmEdizione(null);

		return smLinkEdizioneFunction;
	}

	public SmPopulation addSmPopulation(SmPopulation smPopulation) {
		getSmPopulations().add(smPopulation);
		smPopulation.setSmEdizione(this);

		return smPopulation;
	}

	public SmPopulation removeSmPopulation(SmPopulation smPopulation) {
		getSmPopulations().remove(smPopulation);
		smPopulation.setSmEdizione(null);

		return smPopulation;
	}

	public SmUnitEditionFase addSmUnitEditionFas(SmUnitEditionFase smUnitEditionFas) {
		getSmUnitEditionFases().add(smUnitEditionFas);
		smUnitEditionFas.setSmEdizione(this);

		return smUnitEditionFas;
	}

	public SmUnitEditionFase removeSmUnitEditionFas(SmUnitEditionFase smUnitEditionFas) {
		getSmUnitEditionFases().remove(smUnitEditionFas);
		smUnitEditionFas.setSmEdizione(null);

		return smUnitEditionFas;
	}

	public SmEdizione(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SmEdizione)) {
			return false;
		}
		SmEdizione other = (SmEdizione) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

}