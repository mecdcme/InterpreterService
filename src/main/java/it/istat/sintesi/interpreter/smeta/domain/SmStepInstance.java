package it.istat.sintesi.interpreter.smeta.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SM_STEP_INSTANCE database table.
 * 
 */
@Entity
@Table(name="SM_STEP_INSTANCE")
@NamedQuery(name="SmStepInstance.findAll", query="SELECT s FROM SmStepInstance s")
public class SmStepInstance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=100)
	private String descr;

	@Column(length=50)
	private String fcall;

	@Column(length=50)
	private String nome;

	//bi-directional many-to-many association to SmBusinessStep
	@ManyToMany
	@JoinTable(
		name="SM_LINK_STEP_INSTANCE"
		, joinColumns={
			@JoinColumn(name="ISTANZA")
			}
		, inverseJoinColumns={
			@JoinColumn(name="STEP")
			}
		)
	private List<SmBusinessStep> smBusinessSteps;

	//bi-directional many-to-one association to SmWorkset
	@OneToMany(mappedBy="smStepInstance")
	private List<SmWorkset> smWorksets;

	public SmStepInstance() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getFcall() {
		return this.fcall;
	}

	public void setFcall(String fcall) {
		this.fcall = fcall;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<SmBusinessStep> getSmBusinessSteps() {
		return this.smBusinessSteps;
	}

	public void setSmBusinessSteps(List<SmBusinessStep> smBusinessSteps) {
		this.smBusinessSteps = smBusinessSteps;
	}

	public List<SmWorkset> getSmWorksets() {
		return this.smWorksets;
	}

	public void setSmWorksets(List<SmWorkset> smWorksets) {
		this.smWorksets = smWorksets;
	}

	public SmWorkset addSmWorkset(SmWorkset smWorkset) {
		getSmWorksets().add(smWorkset);
		smWorkset.setSmStepInstance(this);

		return smWorkset;
	}

	public SmWorkset removeSmWorkset(SmWorkset smWorkset) {
		getSmWorksets().remove(smWorkset);
		smWorkset.setSmStepInstance(null);

		return smWorkset;
	}

}