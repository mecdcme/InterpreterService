/**
 * 
 */
package it.istat.sintesi.interpreter.bean;

import lombok.Data;

/**
 * @author framato
 *
 */
@Data
public class DataBean {

	DataModelBean dataModel;
	Object data;
	
	public DataBean(DataModelBean dmb, Object data) {
		this.setDataModel(dmb);
		this.setData(data);
	}
	
	public DataBean(UnitDBModel umb, Object data) {
		this.setDataModel(new DataModelBean(umb));
		this.setData(data);
	}
	
}
