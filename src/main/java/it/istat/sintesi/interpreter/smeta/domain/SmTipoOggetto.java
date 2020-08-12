package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the SM_TIPO_OGGETTO database table.
 * 
 */
@Data
@Entity
@Table(name="SM_TIPO_OGGETTO")
@NamedQuery(name="SmTipoOggetto.findAll", query="SELECT s FROM SmTipoOggetto s")
public class SmTipoOggetto implements Serializable {
	private static final long serialVersionUID = 1L;
	public  static final short UNIT = 1;
	public  static final short VAR = 2;
	public  static final short RULE_TRANSFORMATION = 3;
	public  static final short RULE_SELECTION = 4;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Short id;

	@Column(length=255)
	private String descr;

	@Column(length=32)
	private String nome;

	//bi-directional many-to-one association to SmWorkset
	@OneToMany(mappedBy="smTipoOggetto")
	private List<SmWorkset> smWorksets;

	public SmTipoOggetto() {
	}

	

	public SmWorkset addSmWorkset(SmWorkset smWorkset) {
		getSmWorksets().add(smWorkset);
		smWorkset.setSmTipoOggetto(this);

		return smWorkset;
	}

	public SmWorkset removeSmWorkset(SmWorkset smWorkset) {
		getSmWorksets().remove(smWorkset);
		smWorkset.setSmTipoOggetto(null);

		return smWorkset;
	}

}