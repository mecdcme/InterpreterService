package it.istat.sintesi.interpreter.domain.wd;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.istat.sintesi.interpreter.domain.survey.User;
import lombok.Data;

@Data
@Entity
@Table(name = "WD_EDIZIONE")
public class WdEdizione implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;

	@Column(name = "INDAGINE")
	private String indagine;
	
	@Column(name = "EDIZIONE")
	private Long edizione;

	@Column(name = "ANNO")
	private Short anno;

	@Column(name = "MESE")
	private Short mese;

	@Column(name = "TIPO")
	private String tipo;
	
	@Column(name = "PERIODICITA")
	private Short periodicita;

	@OneToOne
	@JoinColumn(name = "USER_LAVORAZIONE")
	private User userLavorazione;

	@Column(name = "DATA_ULTIMA_LAVORAZIONE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimaLavorazione;

	@Column(name = "USER_ULTIMA_LAVORAZIONE")
	private String userUltimaLavorazione;
}
