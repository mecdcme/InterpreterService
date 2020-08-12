package it.istat.sintesi.interpreter.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class CheckedRule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6455343146865332721L;

	 
    private KeysDataBean data;
	private String ruleCode;
	private String description;
	private String formula;
	private String msg;
	private int validate;
 

	

}
