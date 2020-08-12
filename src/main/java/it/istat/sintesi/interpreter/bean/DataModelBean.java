/**
 * 
 */
package it.istat.sintesi.interpreter.bean;

import java.util.List;

import lombok.Data;

/**
 * @author framato
 *
 */
@Data
public class DataModelBean {

	List<String> fieldList;
	List<String> keyList;
	Long idUnit;

	public DataModelBean(UnitDBModel unitDM) {
		this.setFieldList(unitDM.getNomeCampiSemplici());
		this.setKeyList(unitDM.getChiave());
		this.setIdUnit(unitDM.getId());
	}
}
