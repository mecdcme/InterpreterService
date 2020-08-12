package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the SM_DATASET database table.
 * 
 */
@Data
@Entity
@Table(name="SM_DATASET")
@NamedQuery(name="SmDataset.findAll", query="SELECT s FROM SmDataset s")
public class SmDataset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(  length=100)
	@JoinColumn(name="TABLE_NAME")
	private String tableName;
	@Column(  length=100)
	@JoinColumn(name="TABLE_KEY")
	private String tableKey;
	
	@Column(  length=100)
	@JoinColumn(name="TABLE_MASTER")
	private String tableMaster;
	@Column(  length=100)
	@JoinColumn(name="KEY_MASTER")
	private String keyMaster;

	//bi-directional many-to-one association to SmTipoTabella
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TIPO_TABELLA")
	private SmTipoTabella smTipoTabella;

	//bi-directional many-to-one association to SmUnit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UNIT")
	private SmUnit smUnit;

	public SmDataset() {
	}

	 

}