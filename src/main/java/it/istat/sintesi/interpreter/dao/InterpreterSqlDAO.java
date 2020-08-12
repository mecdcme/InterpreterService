package it.istat.sintesi.interpreter.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.istat.sintesi.interpreter.bean.LogicalRelation;
import it.istat.sintesi.interpreter.bean.UnitDBModel;
import it.istat.sintesi.interpreter.smeta.domain.SmEdizUnitVar;
import it.istat.sintesi.interpreter.utils.PojoUtiliy;
import oracle.sql.NUMBER;

@Repository
public class InterpreterSqlDAO {
	// static Logger log = Logger.getRootLogger();

	@Autowired
	private EntityManager em;

	public List<Object[]> getGenericSQL(String query, Map<String, String> parametri) {

		Query q = em.createNativeQuery(query);

		for (String key : parametri.keySet()) {
			String val = parametri.get(key);
			q.setParameter(key, val);

		}

		return (List<Object[]>) q.getResultList();
	}

	public Map<String, Object> getGenericSQLParamsList(String query, List<String> parametri) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		TypedQuery<Object[]> q = (TypedQuery) em.createNativeQuery(query, Object[].class);

		int i = 1;
		for (Iterator iterator = parametri.iterator(); iterator.hasNext();) {
			String val = (String) iterator.next();
			q.setParameter(i, val);
			i++;

		}

		List<Object[]> list = q.getResultList();

		for (Object result : list) {
			map.put(result.toString(), result);
		}

		return map;

	}

	public Object getOneGenericSQLParamsList(String query, List<String> parametri) {

		Query q = em.createNativeQuery(query);

		int i = 1;
		for (Iterator iterator = parametri.iterator(); iterator.hasNext();) {
			String val = (String) iterator.next();
			q.setParameter(i, val);
			i++;

		}

		return (Object) q.getSingleResult();
	}

	// Return a Map Values
	public Map<?, ?> getOneAsMapGenericSQLParamsMap(UnitDBModel mdb, String query, List<LogicalRelation> listRL,
			List<String> campiSelect) throws Exception {
		HashMap<String, Object> ret = new HashMap<String, Object>();

		Query q = em.createNativeQuery(query);

		int i = 1;

		for (Iterator iterator = listRL.iterator(); iterator.hasNext();) {
			LogicalRelation lr = (LogicalRelation) iterator.next();
			String v = lr.getItem2();
			if (v == null)
				v = "";
			q.setParameter(i, v);
			i++;

		}
		Object[] ris = (Object[]) q.getSingleResult();

		for (int j = 0; j < campiSelect.size(); j++) {
			Object val = ris[j];
			if (val == null)
				val = "";
			ret.put(campiSelect.get(j).toUpperCase(), val);
		}
		// bean=Utility.creaPojoValoridaMDB( mdb,ret);

		return ret;
	}

	// Return a Map Values
	public Map<?, ?> getOneAsMapGenericSQLParamsMap(UnitDBModel mdb, String query, Map<String, String> parametri,
			List<String> campiSelect) throws Exception {
		HashMap<String, Object> ret = new HashMap<String, Object>();

		Query q = em.createNativeQuery(query);

		int i = 1;

		for (Iterator iterator = parametri.keySet().iterator(); iterator.hasNext();) {
			String k = (String) iterator.next();
			String v = parametri.get(k);
			if (v == null)
				v = "";
			q.setParameter(i, v);
			i++;

		}
		Object[] ris = (Object[]) q.getSingleResult();

		for (int j = 0; j < campiSelect.size(); j++) {
			Object val = ris[j];
			if (val == null)
				val = "";
			ret.put(campiSelect.get(j).toUpperCase(), val);
		}
		// bean=Utility.creaPojoValoridaMDB( mdb,ret);

		return ret;
	}

	// Return a of Pojo obect
	public Object getOneGenericSQLParamsList(UnitDBModel mdb, String query, List<String> parametri) throws Exception {
		HashMap<String, Object> ret = new HashMap<String, Object>();
		Object bean = null;
		Query q = em.createNativeQuery(query);

		int i = 1;
		for (Iterator iterator = parametri.iterator(); iterator.hasNext();) {
			String val = (String) iterator.next();
			q.setParameter(i, val);
			i++;

		}
		Object[] ris = (Object[]) q.getSingleResult();
		Object[] campi = (Object[]) mdb.getCampiSemplici().toArray();
		for (int j = 0; j < ris.length; j++) {
			Object val = ris[j];
			if (val == null)
				val = "";
			ret.put(((SmEdizUnitVar) campi[j]).getSmVariable().getIdVariable(), val);
		}
		bean = PojoUtiliy.creaPojoValoridaMDB(mdb, ret);

		return bean;
	}

	// Return a of Pojo obect
	public Object getOneGenericSQLObjParamsMap(UnitDBModel mdb, String query, Map<String, String> parametri,
			List<String> campiSelect) throws Exception {
		HashMap<String, Object> ret = new HashMap<String, Object>();
		Object bean = null;
		Query q = em.createNativeQuery(query);
		int i = 1;

		for (Iterator iterator = mdb.getChiave().iterator(); iterator.hasNext();) {
			String k = (String) iterator.next();
			String v = parametri.get(k);
			if (v == null)
				v = "";
			q.setParameter(i, v);
			i++;

		}
		Object[] ris = (Object[]) q.getSingleResult();

		for (int j = 0; j < campiSelect.size(); j++) {
			Object val = ris[j];

			if (val == null)
				val = "";
			ret.put(campiSelect.get(j), val);

		}
		bean = PojoUtiliy.creaPojoValoridaMDB(mdb, ret);

		return bean;
	}

	// Return a list of Pojo obects by Maps Params
	public List<Object> getListObjsGenericSQLParamsMap(UnitDBModel mdb, String query, Map<String, String> parametri,
			List<String> campiSelect) throws Exception {

		List<Object> listRet = new ArrayList<Object>();

		Query q = em.createNativeQuery(query);

		int i = 1;
		for (Iterator iterator = parametri.keySet().iterator(); iterator.hasNext();) {
			String k = (String) iterator.next();
			String v = parametri.get(k);
			if (v == null)
				v = "";
			q.setParameter(i, v);
			i++;

		}
		List<Object[]> risList = q.getResultList();

		for (Iterator iterator = risList.iterator(); iterator.hasNext();) {
			Object[] ris = (Object[]) iterator.next();
			HashMap<String, Object> mapval = new HashMap<String, Object>();

			for (int j = 0; j < campiSelect.size(); j++) {
				Object val = ris[j];

				if (val == null)
					val = "";
				mapval.put(campiSelect.get(j), val);
			}
			Object bean = PojoUtiliy.creaPojoValoridaMDB(mdb, mapval);
			listRet.add(bean);
		}

		return listRet;
	}

	// Return a list of Pojo obects
	public List<Object> getListObjsGenericSQLParamsList(UnitDBModel mdb, String query, List<String> parametri,
			List<String> campiSelect) throws Exception {

		List<Object> listRet = new ArrayList<Object>();

		Query q = em.createNativeQuery(query);

		int i = 1;
		for (Iterator iterator = parametri.iterator(); iterator.hasNext();) {
			String val = (String) iterator.next();
			q.setParameter(i, val);
			i++;

		}
		List<Object[]> risList = q.getResultList();

		for (Iterator iterator = risList.iterator(); iterator.hasNext();) {
			Object[] ris = (Object[]) iterator.next();
			HashMap<String, Object> mapval = new HashMap<String, Object>();

			for (int j = 0; j < campiSelect.size(); j++) {
				Object val = ris[j];

				if (val == null)
					val = "";
				mapval.put(campiSelect.get(j), val);
			}
			Object bean = PojoUtiliy.creaPojoValoridaMDB(mdb, mapval);
			listRet.add(bean);
		}

		return listRet;
	}

	// Return a list of Map Values
	public List<Object> getListGenericSQLParamsListMapValue(UnitDBModel mdb, String query, List<String> parametri,
			List<String> campiSelect) throws Exception {

		List<Object> listRet = new ArrayList<Object>();

		Query q = em.createNativeQuery(query);

		int i = 1;
		for (Iterator iterator = parametri.iterator(); iterator.hasNext();) {
			String val = (String) iterator.next();
			q.setParameter(i, val);
			i++;

		}
		List<Object[]> risList = q.getResultList();

		for (Iterator iterator = risList.iterator(); iterator.hasNext();) {
			Object[] ris = (Object[]) iterator.next();
			HashMap<String, Object> mapval = new HashMap<String, Object>();

			for (int j = 0; j < campiSelect.size(); j++) {
				Object val = ris[j];

				if (val == null)
					val = "";
				mapval.put(campiSelect.get(j).toUpperCase(), val);
			}

			listRet.add(mapval);
		}

		return listRet;
	}

	// Return a list of Maps elements by Maps Params
	public List<Object> getListMapsValuesGenericSQLParamsMap(UnitDBModel mdb, String query,
			List<LogicalRelation> listRL, List<String> campiSelect) throws Exception {

		List<Object> listRet = new ArrayList<Object>();

		Query q = em.createNativeQuery(query);

		int i = 1;
		for (Iterator iterator = listRL.iterator(); iterator.hasNext();) {
			LogicalRelation lr = (LogicalRelation) iterator.next();
			String v = lr.getItem2();
			if (v == null)
				v = "";
			q.setParameter(i, v);
			i++;
		}
		List<Object[]> risList = q.getResultList();

		for (Iterator iterator = risList.iterator(); iterator.hasNext();) {
			Object[] ris = (Object[]) iterator.next();
			HashMap<String, Object> mapval = new HashMap<String, Object>();

			for (int j = 0; j < campiSelect.size(); j++) {
				Object val = ris[j];

				// if (val == null) val = "";
				mapval.put(campiSelect.get(j).toUpperCase(), val);
			}

			listRet.add(mapval);
		}

		return listRet;
	}

	@Transactional
	public int updateGenericSQLMap(String query, List<String> values) throws Exception {

		int ret = 0;

		Query q = em.createNativeQuery(query);

		int i = 1;
		for (Iterator iterator = values.iterator(); iterator.hasNext();) {
			String value = (String) iterator.next();

			if (value == null || value.equals("null")) value="";
				q.setParameter(i, value);
			i++;
		}
		ret = q.executeUpdate();

		return ret;
	}

	public int getSizeGenericSQLMapParamsList(UnitDBModel mdb, String selectAll, List<String> params) {
		// TODO Auto-generated method stub
		return 0;
	}
}
