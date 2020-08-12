package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the SM_TIPO_TABELLA database table.
 * 
 */
@Entity
@Table(name="SM_TIPO_TABELLA")
@NamedQuery(name="SmTipoTabella.findAll", query="SELECT s FROM SmTipoTabella s")
public class SmTipoTabella implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=50)
	private String descrizione;

	@Column(name="FLAG_BACKUP", precision=1)
	private BigDecimal flagBackup;

	//bi-directional many-to-one association to SmDataset
	@OneToMany(mappedBy="smTipoTabella")
	private List<SmDataset> smDatasets;

	public SmTipoTabella() {
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

	public BigDecimal getFlagBackup() {
		return this.flagBackup;
	}

	public void setFlagBackup(BigDecimal flagBackup) {
		this.flagBackup = flagBackup;
	}

	public List<SmDataset> getSmDatasets() {
		return this.smDatasets;
	}

	public void setSmDatasets(List<SmDataset> smDatasets) {
		this.smDatasets = smDatasets;
	}

	public SmDataset addSmDataset(SmDataset smDataset) {
		getSmDatasets().add(smDataset);
		smDataset.setSmTipoTabella(this);

		return smDataset;
	}

	public SmDataset removeSmDataset(SmDataset smDataset) {
		getSmDatasets().remove(smDataset);
		smDataset.setSmTipoTabella(null);

		return smDataset;
	}

}