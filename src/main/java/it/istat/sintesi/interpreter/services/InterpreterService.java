/**
 * 
 */
package it.istat.sintesi.interpreter.services;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.istat.sintesi.interpreter.bean.DataBean;
import it.istat.sintesi.interpreter.bean.DataModelBean;
import it.istat.sintesi.interpreter.bean.KeysDataBean;
import it.istat.sintesi.interpreter.bean.LogicalRelation;
import it.istat.sintesi.interpreter.bean.UnitDBModel;
import it.istat.sintesi.interpreter.dao.InterpreterSqlDAO;
import it.istat.sintesi.interpreter.dao.RuleSetDAO;
import it.istat.sintesi.interpreter.dao.SmStepInstanceDAO;
import it.istat.sintesi.interpreter.dao.SmUnitDAO;
import it.istat.sintesi.interpreter.domain.wd.WdEdizione;
import it.istat.sintesi.interpreter.smeta.domain.SmBusinessStep;
import it.istat.sintesi.interpreter.smeta.domain.SmEdizUnitVar;
import it.istat.sintesi.interpreter.smeta.domain.SmEdizione;
import it.istat.sintesi.interpreter.smeta.domain.SmFase;
import it.istat.sintesi.interpreter.smeta.domain.SmRule;
import it.istat.sintesi.interpreter.smeta.domain.SmRuleset;
import it.istat.sintesi.interpreter.smeta.domain.SmStepInstance;
import it.istat.sintesi.interpreter.smeta.domain.SmTipoOggetto;
import it.istat.sintesi.interpreter.smeta.domain.SmUnit;
import it.istat.sintesi.interpreter.smeta.domain.SmWorkset;
import it.istat.sintesi.interpreter.utils.Interpreter_CONST;
import it.istat.sintesi.interpreter.utils.Utility;

/**
 * @author framato
 *
 */
@Service
public class InterpreterService {
	static Logger log = Logger.getRootLogger();
	@Autowired
	private InterpreterSqlDAO interpreterSqlDAO;
	@Autowired
	private SmUnitDAO unitDAO;
	@Autowired
	private SmStepInstanceDAO sIstanceDAO;
	@Autowired
	private RuleService ruleService;
	@Autowired
	private RuleSetDAO ruleSetDAO;

	public UnitDBModel getUnitDbModel(Long edizioneId, Long faseId) throws Exception {
		// TODO Auto-generated method stub
		SmUnit unit = unitDAO.findUnitByEdizioneAndFase(new SmEdizione(edizioneId), new SmFase(faseId));

		UnitDBModel mdb = new UnitDBModel(unit);
		return mdb;
	}

	public List<Object> loadAllData(UnitDBModel mdb) throws Exception {
		// TODO Auto-generated method stub
		return interpreterSqlDAO.getListMapsValuesGenericSQLParamsMap(mdb,
				mdb.selectCampiWhere(Arrays.asList("*"), new ArrayList<>()), new ArrayList<>(), Arrays.asList("*"));

	}

	/**
	 * @return
	 * @throws Exception
	 */
	public DataBean getUnitMicroDataList(WdEdizione eiwf, SmFase fase, String keyParams) throws Exception {
		// TODO Auto-generated method stub

		SmEdizione edizione = new SmEdizione(eiwf.getEdizione());
		SmUnit unit = unitDAO.findUnitByEdizioneAndFase(edizione, fase);

		UnitDBModel unitDM = new UnitDBModel(unit);
		List<LogicalRelation> listRL = Utility.getListLogicalRelationships(keyParams);
		Object microdata = loadMicroData(unitDM, listRL);
		DataBean dataBean = new DataBean(unitDM, microdata);
		return dataBean;
	}

	/**
	 * @param eiwf
	 * @param keyParams
	 * @return
	 * @throws Exception
	 */
	public DataBean getUnitMicroDataFirst(WdEdizione eiwf, SmFase fase, String keyParams) throws Exception {

		SmUnit unit = unitDAO.findUnitByEdizioneAndFase(new SmEdizione(eiwf.getEdizione()), fase);

		UnitDBModel unitDM = new UnitDBModel(unit);

		Object microdata = null;
		List<LogicalRelation> listRL = Utility.getListLogicalRelationships(keyParams);
		List dataList = loadMicroData(unitDM, listRL);
		if (dataList != null && dataList.size() > 0)
			microdata = dataList.get(0);

		DataBean dataBean = new DataBean(unitDM, microdata);

		return dataBean;
	}

	/**
	 * @param mdb
	 * @param keyParams
	 * @return
	 */
	public List loadMicroData(UnitDBModel mdb, List<LogicalRelation> listRL) throws Exception {
		// TODO Auto-generated method stub

		List<String> campiSelect = new ArrayList<>();
		List<String> campiSelectforQuery = new ArrayList<>();
		mdb.getCampiSemplici().forEach((el) -> {
			campiSelectforQuery
					.add((el.getAliasSql() == null || el.getAliasSql().isEmpty()) ? el.getSmVariable().getIdVariable()
							: el.getAliasSql());
			campiSelect.add(el.getSmVariable().getIdVariable());
		});

		List dataList = interpreterSqlDAO.getListMapsValuesGenericSQLParamsMap(mdb,
				mdb.selectWithInQueryWhereListLR(campiSelectforQuery, listRL), listRL, campiSelect);
		return dataList;

	}

	/**
	 * @param keyParams
	 * @return
	 */
	private List<String> getConditionListFromLogicalRelationList(List<LogicalRelation> listRL) {
		// TODO Auto-generated method stub
		List<String> conditions = new ArrayList<>();
		listRL.forEach((el) -> conditions.add(el.getItem1()));
		return conditions;
	}

	private List<String> getConditionListFromParamsMap(Map<String, String> params) {
		// TODO Auto-generated method stub
		List<String> conditions = new ArrayList<>();
		params.keySet().forEach((el) -> conditions.add(el.toUpperCase()));

		return conditions;
	}

	/**
	 * @param edizione
	 * @param step
	 * @return
	 * @throws Exception
	 */
	public Object doStep(Long edizioneId, Long step) throws Exception {

		// 1 Get Business step
		SmStepInstance sIstance = sIstanceDAO.findById(step).orElse(new SmStepInstance());

		SmEdizione edizione = new SmEdizione(edizioneId);
		// List dataOutList = (List) copyRowsData(edizione, sIstance);

		Method method = this.getClass().getDeclaredMethod(sIstance.getFcall(), edizione.getClass(),
				sIstance.getClass());

		List dataOutList = (List) method.invoke(this, edizione, sIstance);
		// List List dataOutList = = (List) validateRules(edizione, sIstance);

		// DataModelBean dmb = new DataModelBean();
		// dmb.setMicroData(dataOutList);
		// dmb.setFieldList(outputUnitDM.getNomeCampiSemplici());
		// dmb.setKeyList(outputUnitDM.getChiave());
		// dmb.setIdUnit(outputUnitDM.getId());

		return dataOutList;

	}

	public Object validateRules(SmEdizione edizione, SmStepInstance sIstance) throws Exception {

		// List<LogicalRelation> listRL =
		// Utility.getListLogicalRelationships(keyParams);

		List<SmWorkset> worksetList = sIstance.getSmWorksets();

		Map<String, ?> worksetMap = parseWorkset(worksetList);

		// get input conditions
		SmRuleset inputCondition = (SmRuleset) worksetMap.get(Interpreter_CONST.WS_INPUT_SELECTION_RULES);
		List<LogicalRelation> listRL = Utility.getListLogicalRelationshipsFromRuleSet(inputCondition);
		// get input MD
		SmUnit inputUnit = (SmUnit) worksetMap.get(Interpreter_CONST.WS_INPUT_UNIT);
		UnitDBModel inputUnitDM = new UnitDBModel(inputUnit);
		List dataInputList = loadMicroData(inputUnitDM, listRL);

		// get validation rule
		SmRuleset validationRuleSet = (SmRuleset) worksetMap.get(Interpreter_CONST.WS_TRANSFORMATION_RULES);

		List reportValidate = validateDataRules(dataInputList, inputUnitDM.getChiave(), validationRuleSet);

		return reportValidate;// manageOutput(output, output_mode);

	}

	public Object viewData(SmEdizione edizione, SmStepInstance sIstance) throws Exception {

		// List<LogicalRelation> listRL =
		// Utility.getListLogicalRelationships(keyParams);

		List<SmWorkset> worksetList = sIstance.getSmWorksets();

		Map<String, ?> worksetMap = parseWorkset(worksetList);

		// get input conditions
		SmRuleset inputCondition = (SmRuleset) worksetMap.get(Interpreter_CONST.WS_INPUT_SELECTION_RULES);
		List<LogicalRelation> listRL = Utility.getListLogicalRelationshipsFromRuleSet(inputCondition);
		// get input MD
		SmUnit inputUnit = (SmUnit) worksetMap.get(Interpreter_CONST.WS_INPUT_UNIT);
		UnitDBModel inputUnitDM = new UnitDBModel(inputUnit);
		List dataInputList = loadMicroData(inputUnitDM, listRL);
		return dataInputList;// manageOutput(output, output_mode);

	}

	/**
	 * @param dataInputList
	 * @param keysList
	 * @param validationRuleSet
	 * @return
	 */
	private List validateDataRules(List dataInputList, List<String> keysList, SmRuleset validationRuleSet) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<Object> listRet = new ArrayList<Object>();
		List<SmRule> validationRules = validationRuleSet.getSmRules();

		for (Iterator iterator = dataInputList.iterator(); iterator.hasNext();) {
			@SuppressWarnings("unchecked")
			HashMap<String, ?> inputRow = (HashMap<String, ?>) iterator.next();
			KeysDataBean keysDataRow = Utility.createKeysData(inputRow, keysList);
			List checkedRulesList = ruleService.applyValidationRulesEL(keysDataRow, inputRow, validationRules);

			listRet.add(checkedRulesList);
		}

		return listRet;
	}
	
	private Object copyRowsData(SmEdizione edizione, SmStepInstance sIstance) throws Exception {

		List<SmWorkset> worksetList = sIstance.getSmWorksets();

		Map<String, ?> worksetMap = parseWorkset(worksetList);

		// get input conditions
		SmRuleset inputCondition = (SmRuleset) worksetMap.get(Interpreter_CONST.WS_INPUT_SELECTION_RULES);
		List<LogicalRelation> listRL = Utility.getListLogicalRelationshipsFromRuleSet(inputCondition);
		// get input MD
		SmUnit inputUnit = (SmUnit) worksetMap.get(Interpreter_CONST.WS_INPUT_UNIT);
		UnitDBModel inputUnitDM = new UnitDBModel(inputUnit);
		List dataInputList = loadMicroData(inputUnitDM, listRL);

		// get transform rule
		SmRuleset transformationRuleSet = (SmRuleset) worksetMap.get(Interpreter_CONST.WS_TRANSFORMATION_RULES);

		// get Output Unit
		SmUnit outputUnit = (SmUnit) worksetMap.get(Interpreter_CONST.WS_OUTPUT_UNIT);
		UnitDBModel outputUnitDM = new UnitDBModel(outputUnit);
		List dataOutList = createListMDataOutput(dataInputList, outputUnitDM, transformationRuleSet);

		int ret = storeData(outputUnitDM, dataOutList);
		return dataOutList;// manageOutput(output, output_mode);

	}

	/**
	 * @param outputUnitDM
	 * @param dataOutList
	 */

	private int storeData(UnitDBModel outputUnitDM, List dataOutList) {

		int ret = 0;
		String queryInsert = outputUnitDM.insertQuery();
		List<String> fields = new ArrayList<String>();
		outputUnitDM.getCampiSemplici()
				.forEach(smEdizUnitVar -> fields.add(smEdizUnitVar.getSmVariable().getIdVariable()));

		for (Iterator iterator = dataOutList.iterator(); iterator.hasNext();) {
			HashMap<String, Object> outputRow = (HashMap<String, Object>) iterator.next();
			try {
				ret += interpreterSqlDAO.updateGenericSQLMap(queryInsert,
						Utility.getListaValoriParametriDaHAsh(outputRow, fields));
			} catch (Exception e) {
				log.error("Update errror:" + queryInsert);
				log.error("dati:" + outputRow);
				log.error("Errore:" + e.getMessage());
				 
			}
		}
		return ret;

	}

	/**
	 * @param dataInputList
	 * @param outputUnit
	 * @param listRLTransf
	 * @return
	 */
	private List createListMDataOutput(List dataInputList, UnitDBModel outputUnit, SmRuleset transformationRuleSet) {
		// TODO Auto-generated method stub
		List<Object> listRet = new ArrayList<Object>();
		List<SmRule> transformationRules = transformationRuleSet.getSmRules();

		for (Iterator iterator = dataInputList.iterator(); iterator.hasNext();) {
			@SuppressWarnings("unchecked")
			HashMap<String, ?> inputRow = (HashMap<String, ?>) iterator.next();

			HashMap<String, Object> outputRow = new HashMap<String, Object>();
			outputUnit.getCampiSemplici().forEach(smEdizUnitVar -> {
				String field = smEdizUnitVar.getSmVariable().getIdVariable().toUpperCase();
				Object outVal = inputRow.get(field);
				outputRow.put(field, outVal);
			});

			ruleService.applyTransformationRulesEL(inputRow, outputRow, transformationRules);

			listRet.add(outputRow);
		}

		return listRet;
	}

	/**
	 * @param worksetList
	 * @return
	 */
	private Map<String, ?> parseWorkset(List<SmWorkset> worksetList) {
		// TODO Auto-generated method stub

		Map<String, Object> ret = new HashMap<String, Object>();
		worksetList.forEach(workset -> {
			if (workset.getTipoIO().equals(Interpreter_CONST.TIPO_IO_INPUT)) { // Input
				if (workset.getSmTipoOggetto().getId().equals(SmTipoOggetto.UNIT)) {
					SmUnit ui = unitDAO.findById(workset.getIdOggetto()).orElse(null);

					ret.put(Interpreter_CONST.WS_INPUT_UNIT, ui);
				} else if (workset.getSmTipoOggetto().getId().equals(SmTipoOggetto.VAR))
					ret.put(Interpreter_CONST.WS_INPUT_VARIABLE, workset);
				else if (workset.getSmTipoOggetto().getId().equals(SmTipoOggetto.RULE_SELECTION)) {
					SmRuleset rs = ruleSetDAO.findById(workset.getIdOggetto()).orElse(null);
					ret.put(Interpreter_CONST.WS_INPUT_SELECTION_RULES, rs);
				} else if (workset.getSmTipoOggetto().getId().equals(SmTipoOggetto.RULE_TRANSFORMATION)) {
					SmRuleset rs = ruleSetDAO.findById(workset.getIdOggetto()).orElse(null);
					ret.put(Interpreter_CONST.WS_TRANSFORMATION_RULES, rs);
				}
			} else { // OUTPUT
				SmUnit uo = unitDAO.findById(workset.getIdOggetto()).orElse(null);
				ret.put(Interpreter_CONST.WS_OUTPUT_UNIT, uo);
			}

		});

		return ret;
	}

}
