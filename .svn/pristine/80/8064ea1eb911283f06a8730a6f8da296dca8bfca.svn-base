package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the SM_FASE database table.
 * 
 */
@Data
@Entity
@Table(name="SM_FASE")
@NamedQuery(name="SmFase.findAll", query="SELECT s FROM SmFase s")
public class SmFase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=100)
	private String descr;

	@Column(length=50)
	private String nome;

	//bi-directional many-to-one association to SmUnitEditionFase
	@OneToMany(mappedBy="smFase")
	private List<SmUnitEditionFase> smUnitEditionFases;

	public SmFase() {
	}

	public SmFase(Long id) {
		this.id=id;
	}
	 
	public SmUnitEditionFase addSmUnitEditionFas(SmUnitEditionFase smUnitEditionFas) {
		getSmUnitEditionFases().add(smUnitEditionFas);
		smUnitEditionFas.setSmFase(this);

		return smUnitEditionFas;
	}

	public SmUnitEditionFase removeSmUnitEditionFas(SmUnitEditionFase smUnitEditionFas) {
		getSmUnitEditionFases().remove(smUnitEditionFas);
		smUnitEditionFas.setSmFase(null);

		return smUnitEditionFas;
	}

}