package it.istat.sintesi.interpreter.utils;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;

import it.istat.sintesi.interpreter.bean.UnitDBModel;
import it.istat.sintesi.interpreter.smeta.domain.SmEdizUnitVar;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

public class PojoUtiliy {
	public static Logger log = Logger.getLogger("PojoUtiliy");

	public static Object creaPojoValoridaMDB(UnitDBModel mdb, Map<String, Object> valoriVariabile)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub

		//verifico se esiste la Classe dell'oggeto ed eventualmente la creo
		creaGeneratedPOJO(mdb);
		
		Object obj = null;

		Class clazz = Loader.loadClass(getPojoName(mdb));
		// clazz = ClassLoader.getSystemClassLoader().loadClass(getPojoName(mdb));
		// ClassLoader cls = ClassLoader.getSystemClassLoader();
		// Class clazz= Class.forName(getPojoName(mdb),true,cls);
		// clazz = Class.forName(getPojoName(mdb));
		// Class.forName(RuleBox_KOST.NAMESPACE_POJO+mdb.getEtichetta().toLowerCase() );
		obj = clazz.newInstance();

		for (Iterator iterator = mdb.getCampiSemplici().iterator(); iterator.hasNext();) {
			SmEdizUnitVar ci = (SmEdizUnitVar) iterator.next();
			Class cli = String.class;
			Object rets = valoriVariabile.get(ci.getSmVariable().getIdVariable().toLowerCase());

			if ("NUMERIC".equalsIgnoreCase(ci.getSmVariable().getSmVariabileType().getNomeTipoVariabileEng())) {
				cli = BigDecimal.class;
				rets = new BigDecimal(0);
				try {
					rets = new BigDecimal(String.valueOf(valoriVariabile.get(ci.getSmVariable().getIdVariable())));
				} catch (Exception e) {
				}

			}
			try {
				clazz.getMethod(
						"set" + ci.getSmVariable().getIdVariable().substring(0, 1).toUpperCase() + ci.getSmVariable().getIdVariable().toLowerCase().substring(1),
						cli).invoke(obj, rets);

			} catch (Exception e) {
				log.debug(ci.getSmVariable().getIdVariable().toLowerCase());
				log.debug(cli.getName().toLowerCase());
				log.debug(valoriVariabile.get(ci.getSmVariable().getIdVariable().toLowerCase()));

			}
		}

		return obj;
	}

	private static String getPojoName(UnitDBModel mdb) {
		// TODO Auto-generated method stub
		return Interpreter_CONST.NAMESPACE_POJO + mdb.getEtichetta().toLowerCase();
	}

	public static void creaGeneratedPOJO(UnitDBModel mdb) {

		ClassPool pool = ClassPool.getDefault();
		CtClass cc = null;

		try {
			cc = pool.get(getPojoName(mdb));
		} catch (NotFoundException e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage());
		}
		// già caricato
		if (cc != null)
			return;
		Map<String, Class<?>> props = new HashMap<String, Class<?>>();
		try {

			// creo i campi semplici String o Number (INTEGER)
			log.error(mdb.getEtichetta());
			for (Iterator iterator = mdb.getCampiSemplici().iterator(); iterator.hasNext();) {
				SmEdizUnitVar ci = (SmEdizUnitVar) iterator.next();
				Class cli = String.class;
				if ("NUMERIC".equalsIgnoreCase(ci.getSmVariable().getSmVariabileType().getNomeTipoVariabileEng()))
					cli = BigDecimal.class;
				props.put(ci.getSmVariable().getIdVariable().toLowerCase(), cli);

			}
			// props.put(RuleBox_KOST.STATO_BEAN,String.class);

			// creo i campi lista semplici

			List<UnitDBModel> lc = mdb.getCampiListaComplessi();

			// CtClass arrListClazz =resolveCtClass(ArrayList.class);
			for (Iterator iterator = lc.iterator(); iterator.hasNext();) {
				UnitDBModel mod = (UnitDBModel) iterator.next();
				String k = mod.getEtichetta().toLowerCase();
				log.error(mod.getEtichetta().toLowerCase());
				// creo la classe tipo
				creaGeneratedPOJO(mod);

				props.put(mod.getEtichetta().toLowerCase(), ArrayList.class);

				// da esempio
				// CtClass arrListClazz = ClassPool.getDefault().get("java.util.ArrayList");
				// CtClass point = ClassPool.getDefault().get("Point");
				// CtField f = new CtField(arrListClazz, "someList", point);
				// point.addField(f);

			}

			log.error(mdb.getEtichetta());
			PojoUtiliy.generate(getPojoName(mdb), props);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			log.error(e);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			log.error(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}

	}

	public static void generate(String className, Map<String, Class<?>> properties)
			throws NotFoundException, CannotCompileException, IOException {

		ClassPool pool = ClassPool.getDefault();
		CtClass cc = null;
		boolean prima = true;
		try {
			cc = pool.get(className);
			cc.defrost();
			cc.detach();
			prima = false;
		} catch (Exception e) {
		}

		cc = pool.makeClass(className);

		log.debug("POJO creato: " + className);
		// add this to define a super class to extend
		// cc.setSuperclass(resolveCtClass(MySuperClass.class));
		// add this to define an interface to implement
		cc.addInterface(resolveCtClass(Serializable.class));

		for (Entry<String, Class<?>> entry : properties.entrySet()) {

			cc.addField(new CtField(resolveCtClass(entry.getValue()), entry.getKey(), cc));

			// add getter
			cc.addMethod(generateGetter(cc, entry.getKey(), entry.getValue()));

			// add setter
			cc.addMethod(generateSetter(cc, entry.getKey(), entry.getValue()));
		}

		if (prima)
			cc.toClass();
		else
			cc.rebuildClassFile();
		cc.defrost();

	}

	private static CtMethod generateGetter(CtClass declaringClass, String fieldName, Class fieldClass)
			throws CannotCompileException {

		String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		StringBuffer sb = new StringBuffer();
		sb.append("public ").append(fieldClass.getName()).append(" ").append(getterName).append("(){")
				.append("return this.").append(fieldName).append(";").append("}");
		return CtMethod.make(sb.toString(), declaringClass);
	}

	private static CtMethod generateSetter(CtClass declaringClass, String fieldName, Class fieldClass)
			throws CannotCompileException {

		String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		StringBuffer sb = new StringBuffer();
		sb.append("public void ").append(setterName).append("(").append(fieldClass.getName()).append(" ")
				.append(fieldName).append(")").append("{").append("this.").append(fieldName).append("=")
				.append(fieldName).append(";").append("}");
		return CtMethod.make(sb.toString(), declaringClass);
	}

	private static CtClass resolveCtClass(Class clazza) throws NotFoundException {
		ClassPool pool = ClassPool.getDefault();
		return pool.get(clazza.getName());
	}

}
