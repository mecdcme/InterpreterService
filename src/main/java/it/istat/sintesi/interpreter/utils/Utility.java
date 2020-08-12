package it.istat.sintesi.interpreter.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import it.istat.sintesi.interpreter.utils.Interpreter_CONST;

import org.springframework.util.NumberUtils;
import org.springframework.expression.EvaluationException;

import it.istat.sintesi.interpreter.bean.KeysDataBean;
import it.istat.sintesi.interpreter.bean.LogicalRelation;
import it.istat.sintesi.interpreter.bean.UnitDBModel;
import it.istat.sintesi.interpreter.smeta.domain.SmRuleset;

public class Utility {
	public static Logger log = Logger.getLogger("Utils");

	public static Map<String, String> getMapParams(String keyParams) {
		// TODO Auto-generated method stub
		Map<String, String> ret = new HashMap<>();

		StringTokenizer tokenizer = new StringTokenizer(keyParams, "&");
		while (tokenizer.hasMoreElements()) {
			String couple = (tokenizer.nextToken());
			String[] couplesplit = couple.split("=");
			ret.put(couplesplit[0], couplesplit[1]);
		}

		return ret;
	}

	/*
	 * get List of Relationships es. a <= b
	 */
	public static List<LogicalRelation> getListLogicalRelationships(String keyParams) {
		// TODO Auto-generated method stub
		List<LogicalRelation> ret = new ArrayList<>();
		Pattern pattern = Pattern.compile("(.*?)(=|>|<|<=|>=)(.*)");

		StringTokenizer tokenizer = new StringTokenizer(keyParams, "&");
		while (tokenizer.hasMoreElements()) {
			String relation = (tokenizer.nextToken());
			Matcher matcher = pattern.matcher(relation);

			while (matcher.find()) {
				LogicalRelation lr = new LogicalRelation();
				lr.setItem1(matcher.group(1));
				lr.setRelation(matcher.group(2));
				lr.setItem2(matcher.group(3));
				ret.add(lr);

			}

		}

		return ret;
	}

	public static int aggiornaDatidaObjaMap(Object model, Map<String, String[]> params) {
		// TODO Auto-generated method stub
		int mod = 0;
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			String k = entry.getKey();
			String nv = entry.getValue()[0];
			try {
				Object oldV = Utility.getValore(model, k);
				if (oldV != null && nv != null && !nv.equals(String.valueOf(oldV))) {
					// controllo per le checkboxs trasfomro i true in 1 e 0 in false
					if ("true".equalsIgnoreCase(nv))
						nv = "1";
					if ("false".equalsIgnoreCase(nv))
						nv = "0";
					Utility.setValoreBean(model, k, nv);
					mod++;
				}

			} catch (Exception e) {
				log.debug(e);
			}

		}
		return mod;
	}

	public static int aggiornaDatidaMapaMap(Map<String, Object> params_old, Map<String, String[]> params) {
		// TODO Auto-generated method stub
		int mod = 0;
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			String k = entry.getKey();
			String nv = entry.getValue()[0];
			try {
				Object oldV = params_old.get(k);
				if (oldV != null && nv != null && !isEqualValue(oldV, nv)) {
					// controllo per le checkboxs trasfomro i true in 1 e 0 in false
					if ("true".equalsIgnoreCase(nv))
						nv = "1";
					if ("false".equalsIgnoreCase(nv))
						nv = "0";
					Class nvc = oldV.getClass();
					Object nobj = nvc.getDeclaredConstructor(String.class).newInstance(nv);
					params_old.put(k, nobj);
					mod++;
				}

			} catch (Exception e) {
				log.debug(e);
			}

		}
		return mod;
	}

	public static void setValoreBean(Object model, String key, Object valore) {
		// TODO Auto-generated method stub
		String nomeMetodo = "set" + Character.toUpperCase(key.charAt(0)) + key.substring(1);
		String nomeMetodoGet = "get" + Character.toUpperCase(key.charAt(0)) + key.substring(1);
		Method metodo, metGet;
		try {
			metGet = model.getClass().getMethod(nomeMetodoGet, null);
			Class classeR = metGet.getReturnType();
			// metodo = model.getClass().getMethod(nomeMetodo,valore.getClass());
			metodo = model.getClass().getMethod(nomeMetodo, classeR);
			Class[] params = { valore.getClass() };
			Object arglist[] = new Object[1];

			try {
				// converto i true e i false in 1 0
				if ((valore instanceof String) && ((String) valore).equalsIgnoreCase("true")
						&& classeR.getSuperclass().equals(Number.class))
					valore = "1";
				if ((valore instanceof String) && ((String) valore).equalsIgnoreCase("false")
						&& classeR.getSuperclass().equals(Number.class))
					valore = "0";
			} catch (Exception ex) {
				log.debug(ex);
				log.error(ex);

			}

			Object paramValue[] = new Object[] { valore };
			if (!classeR.getName().equals(ArrayList.class.getName())) {
				Constructor con = classeR.getConstructor(params);
				arglist[0] = con.newInstance(paramValue);
				metodo.invoke(model, arglist);
			} else
				metodo.invoke(model, paramValue);
			log.debug("Bean " + model.getClass().getName() + ", modificato: " + nomeMetodo + ", valore:" + valore);
		} catch (IllegalAccessException ex) {
			log.debug(ex);
			log.error(ex);

		} catch (IllegalArgumentException ex) {
			log.debug(ex);
			log.error(ex);

		} catch (InvocationTargetException ex) {
			log.debug(ex);
			log.error(ex);

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			log.debug(e);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			log.debug(e);
			log.error(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug(e);
			log.error(e);
		}
	}

	public static Object getValore(Object dato, Method metodo) {

		try {
			return metodo.invoke(dato, new Object[] {});
		} catch (IllegalAccessException ex) {
			StringBuilder st = new StringBuilder();
			st.append(Interpreter_CONST.ECCEZIONE_ACCESSO_METODO);
			st.append(metodo.getName());
			log.warn(st.toString(), ex);
			return null;
		} catch (IllegalArgumentException ex) {
			StringBuilder st = new StringBuilder();
			st.append(Interpreter_CONST.ECCEZIONE_ACCESSO_METODO);
			st.append(metodo.getName());
			log.warn(st.toString(), ex);
			return null;
		} catch (InvocationTargetException ex) {
			StringBuilder st = new StringBuilder();
			st.append(Interpreter_CONST.ECCEZIONE_ACCESSO_METODO);
			st.append(metodo.getName());
			log.warn(st.toString(), ex);
			return null;
		}

	}

	public static String getValoreString(Object dato, Method metodo) {
		String ret = null;
		try {
			ret = String.valueOf(metodo.invoke(dato, new Object[] {}));

		} catch (IllegalAccessException ex) {
			StringBuilder st = new StringBuilder();
			st.append(Interpreter_CONST.ECCEZIONE_ACCESSO_METODO);
			st.append(metodo.getName());
			log.warn(st.toString(), ex);
			return null;
		} catch (IllegalArgumentException ex) {
			StringBuilder st = new StringBuilder();
			st.append(Interpreter_CONST.ECCEZIONE_ACCESSO_METODO);
			st.append(metodo.getName());
			log.warn(st.toString(), ex);
			return null;
		} catch (InvocationTargetException ex) {
			StringBuilder st = new StringBuilder();
			st.append(Interpreter_CONST.ECCEZIONE_ACCESSO_METODO);
			st.append(metodo.getName());
			log.warn(st.toString(), ex);
			return null;
		}
		return ret;
	}

	public static Object getValore(Object dato, String idProperty) {
		String nomeMetodo = "get" + Character.toUpperCase(idProperty.charAt(0)) + idProperty.substring(1);

		Method metodo;
		try {
			metodo = dato.getClass().getMethod(nomeMetodo);
		} catch (NoSuchMethodException ex) {
			log.warn("Non trovato metodo get: " + idProperty);
			return null;
		} catch (SecurityException ex) {
			log.warn("Impossibile accedere a metodo get: " + idProperty);
			return null;
		}
		return getValore(dato, metodo);
	}

	public static String getValoreString(Object dato, String idProperty) {
		String nomeMetodo = "get" + Character.toUpperCase(idProperty.charAt(0)) + idProperty.substring(1);

		Method metodo;
		try {
			metodo = dato.getClass().getMethod(nomeMetodo);
		} catch (NoSuchMethodException ex) {
			log.warn("Non trovato metodo get: " + idProperty);
			return null;
		} catch (SecurityException ex) {
			log.warn("Impossibile accedere a metodo get: " + idProperty);
			return null;
		}
		return getValoreString(dato, metodo);
	}

	public static int aggiornaValoriQuestionarioGCompleto(UnitDBModel dataModel, Object dataObj,
			Map<String, String[]> parameterMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void calcolaVariabiliAusiliariEL(Object dataObj, String sezioneSel) {
		// TODO Auto-generated method stub

	}

	public static boolean isEqualValue(Object left, String right) throws EvaluationException {

		try {
			if (left instanceof CharSequence && right instanceof CharSequence) {
				left = left.toString();
				right = right.toString();
				return left.equals(right);
			}

			if (left instanceof Number) {

				Number leftNumber = (Number) left;

				if (leftNumber instanceof BigDecimal) {
					BigDecimal leftBigDecimal = NumberUtils.convertNumberToTargetClass(leftNumber, BigDecimal.class);
					BigDecimal rightBigDecimal = new BigDecimal(right);
					return leftBigDecimal.compareTo(rightBigDecimal) == 0;
				} else if (leftNumber instanceof Double) {
					Double rightDouble = new Double(right);
					return leftNumber.doubleValue() == rightDouble.doubleValue();
				} else if (leftNumber instanceof Float) {
					Float rightNumber = new Float(right);
					return leftNumber.floatValue() > rightNumber.floatValue();
				} else if (leftNumber instanceof BigInteger) {
					BigInteger leftBigInteger = NumberUtils.convertNumberToTargetClass(leftNumber, BigInteger.class);

					BigInteger rightNumber = new BigInteger(right);
					return leftBigInteger.compareTo(rightNumber) == 0;
				} else if (leftNumber instanceof Long) {
					Long rightNumber = new Long(right);
					return leftNumber.longValue() == rightNumber.longValue();
				} else if (leftNumber instanceof Integer) {
					Integer rightNumber = new Integer(right);
					return leftNumber.intValue() == rightNumber.intValue();
				} else if (leftNumber instanceof Short) {
					Short rightNumber = new Short(right);
					return leftNumber.shortValue() == rightNumber.shortValue();
				} else if (leftNumber instanceof Byte) {
					Byte rightNumber = new Byte(right);
					return leftNumber.byteValue() == rightNumber.byteValue();
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}

		return false;

	}

	/**
	 * @param inputCondition
	 * @return
	 */ 
	public static List<LogicalRelation> getListLogicalRelationshipsFromRuleSet(SmRuleset inputCondition) {
		// TODO Auto-generated method stub
      List<LogicalRelation> ret = new ArrayList<>();
      inputCondition.getSmRules().forEach(rule->{
    	  List<LogicalRelation> rulesRelations= getListLogicalRelationships(rule.getFormula());
    	  ret.addAll(rulesRelations);
      });
         return ret;
	}

	public static List<String> getListaValoriParametriDaHAsh(HashMap<String, Object> aa,
			List<String> chiave) {
		// TODO Auto-generated method stub
		List <String> val=new ArrayList<String>();

		for (String cella : chiave )
		{
			Object p= aa.get(cella);
			if(p!=null)			val.add(String.valueOf(p));
			else	val.add(null);

		}
		return val;
	}

	/**
	 * @param inputRow
	 * @param keysList
	 * @return
	 */
	public static KeysDataBean createKeysData(HashMap<String, ?> inputRow, List<String> keysList) {
		// TODO Auto-generated method stub
		
		KeysDataBean keysData=new KeysDataBean();
		keysList.forEach(key->{
			keysData.put(key, inputRow.get(key));
			
		});
		
		return keysData;
	}

	/**
	 * @param outputRow
	 * @param outputUnitDM
	 * @return
	 */
 
}
