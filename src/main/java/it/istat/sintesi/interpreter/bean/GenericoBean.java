package it.istat.sintesi.interpreter.bean;

import java.io.Serializable;

public class GenericoBean implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 4675759189509346435L;
	private String id;
	private String valore;
	private String param1;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

	public String getValore() {
		return valore;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam1() {
		return param1;
	}
}
