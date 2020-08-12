package it.istat.sintesi.interpreter.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import it.istat.sintesi.interpreter.bean.CheckedRule;
import it.istat.sintesi.interpreter.bean.KeysDataBean;
import it.istat.sintesi.interpreter.smeta.domain.SmRule;
import it.istat.sintesi.interpreter.smeta.domain.SmRuleset;
import it.istat.sintesi.interpreter.utils.Interpreter_CONST;

/**
 * Copyright 2019 ISTAT
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence. You may
 * obtain a copy of the Licence at:
 *
 * http://ec.europa.eu/idabc/eupl5
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * Licence for the specific language governing permissions and limitations under
 * the Licence.
 *
 * @author Francesco Amato <framato @ istat.it>
 * @author Mauro Bruno <mbruno @ istat.it>
 * @version 0.1
 */

@Service
public class RuleService {
	static Logger log = Logger.getRootLogger();

	public static final int VALIDATION_OK = 1;
	public static final int VALIDATION_NOK = 0;
	public static final int VALIDATION_ERROR = -1;

	public void applyTransformationRulesEL(HashMap<String, ?> inputRow, HashMap<String, Object> outputRowI,
			List<SmRule> transformationRules) {

		StandardEvaluationContext simpleContext = new StandardEvaluationContext();
		simpleContext.addPropertyAccessor(new MapAccessor());
		simpleContext.setRootObject(inputRow);
		ExpressionParser parser = new SpelExpressionParser();

		transformationRules.forEach(rule -> {
			String variabile = rule.getVariabile().toUpperCase();
			String formula = rule.getFormula();
			String res = null;
			try {
				res = parser.parseExpression(formula).getValue(simpleContext, String.class);
			} catch (Exception e) {
				log.error("ESPRESSIONE NON VALIDA:" + formula);
				log.error("Errore:" + e.getMessage());
			}
			outputRowI.put(variabile, res);

		});

	}

	public List<CheckedRule> applyValidationRulesEL(KeysDataBean keysDataRow, HashMap<String, ?> inputRow,
			List<SmRule> validationRules) {
		// TODO Auto-generated method stub

		StandardEvaluationContext simpleContext = new StandardEvaluationContext();
		simpleContext.addPropertyAccessor(new MapAccessor());
		simpleContext.setRootObject(inputRow);
		ExpressionParser parser = new SpelExpressionParser();

		List<CheckedRule> checkedList = new ArrayList<CheckedRule>();
		for (Iterator<SmRule> iterator = validationRules.iterator(); iterator.hasNext();) {

			SmRule rule = (SmRule) iterator.next();
			CheckedRule checkedRule = new CheckedRule();
			checkedRule.setData(keysDataRow);
			checkedRule.setRuleCode(rule.getCode());
			checkedRule.setFormula(rule.getFormula());
			checkedRule.setDescription(rule.getDescr());
			;
			int resV = VALIDATION_ERROR;
			try {

				String rule_expr = rule.getFormula();
				boolean trueValue = (Boolean) parser.parseExpression(rule_expr).getValue(simpleContext);
				resV = (trueValue) ? 1 : 0;

			} catch (Exception e) {
				resV = VALIDATION_ERROR;
				log.error("-----------" + Interpreter_CONST.ERRORE_VALIDAZIONE_REGOLA + "------------------------");
				log.error("-----------" + rule.getCode() + " ---------------------" + rule.getFormula() + "----");
			}

			checkedRule.setValidate(resV);
			checkedList.add(checkedRule);
		}

		return checkedList;

	}

	public static void main(String[] args) {

		HashMap<String, String> inputRow = new HashMap();
		inputRow.put("Keydata", "201512");
		StandardEvaluationContext simpleContext = new StandardEvaluationContext();
		simpleContext.addPropertyAccessor(new MapAccessor());
		simpleContext.setRootObject(inputRow);
		ExpressionParser parser = new SpelExpressionParser();
		String formula = "(Keydata/100)";
		String res = parser.parseExpression(formula).getValue(simpleContext, String.class);
	

	}

}
