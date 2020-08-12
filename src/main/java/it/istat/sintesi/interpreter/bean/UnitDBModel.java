package it.istat.sintesi.interpreter.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import it.istat.sintesi.interpreter.smeta.domain.SmDataset;
import it.istat.sintesi.interpreter.smeta.domain.SmEdizUnitVar;
import it.istat.sintesi.interpreter.smeta.domain.SmUnit;

import static it.istat.sintesi.interpreter.utils.SqlBuilder.*;

public class UnitDBModel extends SmUnit {

	public UnitDBModel(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public UnitDBModel(SmUnit unit) {
		super(unit);
			// campiSemplici
		// campiListaSemplici
		// campiComplessi
		// campiListaComplessi
	    
		// tabelle
		tabelleSecondarie=new ArrayList();  
	 
		
		unit.getSmDatasets().forEach(dataset-> {
			if (dataset.getTableMaster()== null|| dataset.getTableMaster().trim().length()==0) this.tabellaPrincipale=dataset;
			else  this.tabelleSecondarie.add(dataset);
			
			
		});
		
		this.setEtichetta(unit.getDescrizione());
		ArrayList<SmEdizUnitVar> cs = new ArrayList<>();
		ArrayList<SmEdizUnitVar> cls = new ArrayList<>();
		ArrayList<UnitDBModel> cc = new ArrayList<>();
		ArrayList<UnitDBModel> clc = new ArrayList<>();

		for (Iterator iterator = unit.getSmEdizUnitVars().iterator(); iterator.hasNext();) {
			SmEdizUnitVar vari = (SmEdizUnitVar) iterator.next();
			if (vari.getSmVariable().getTipoVariabile() != null) {
				if (vari.getSmEdizUnitVarModeldata().getFlagLista()  == 0)
					cs.add(vari);
				else
					cls.add(vari);
			}
	/*		if (vari.getIdCampoentita() != null) {
				if (vari.getFlagLista().shortValue() == 0)
					cc.add(new ModelDataBean(campoi.getIdCampoentita()));
				else
					clc.add(new ModelDataBean(campoi.getIdCampoentita()));
			}
		*/	 
		}
		for (Iterator iterator = unit.getSmUnits().iterator(); iterator.hasNext();) {
			SmUnit unti = (SmUnit) iterator.next();
			
			/* DA CONTROLLARE LE RELAZIONI TRA UNIT */
			cc.add(new UnitDBModel(unti));
			clc.add(new UnitDBModel(unti));
		}
		setCampiSemplici(cs);
		setCampiListaSemplici(cls);
		setCampiComplessi(cc);
		setCampiListaComplessi(clc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the indagine
	 */
	public String getIndagine() {
		return indagine;
	}

	/**
	 * @param indagine
	 *            the indagine to set
	 */
	public void setIndagine(String indagine) {
		this.indagine = indagine;
	}

	/**
	 * @return the campiListaSemplici
	 */
	public List<SmEdizUnitVar> getCampiListaSemplici() {
		return campiListaSemplici;
	}

	/**
	 * @param campiListaSemplici
	 *            the campiListaSemplici to set
	 */
	public void setCampiListaSemplici(List<SmEdizUnitVar> campiListaSemplici) {
		this.campiListaSemplici = campiListaSemplici;
		creaMappaListaCampiSemplici();
	}

	/**
	 * @return the campiListaComplessi
	 */
	public List<UnitDBModel> getCampiListaComplessi() {
		return campiListaComplessi;
	}

	/**
	 * @param campiListaComplessi
	 *            the campiListaComplessi to set
	 */
	public void setCampiListaComplessi(List<UnitDBModel> campiListaComplessi) {
		this.campiListaComplessi = campiListaComplessi;
		creaMappaListaCampiComplessi();
	}

	/**
	 * @return the campiListaSempliciMap
	 */
	public Map<String, SmEdizUnitVar> getCampiListaSempliciMap() {
		return campiListaSempliciMap;
	}

	/**
	 * @param campiListaSempliciMap
	 *            the campiListaSempliciMap to set
	 */
	public void setCampiListaSempliciMap(HashMap<String, SmEdizUnitVar> campiListaSempliciMap) {
		this.campiListaSempliciMap = campiListaSempliciMap;
		creaMappaListaCampiSemplici();
	}

	/**
	 * @return the campiListaComplessiMap
	 */
	public Map<String, UnitDBModel> getCampiListaComplessiMap() {
		return campiListaComplessiMap;
	}

	/**
	 * @param campiListaComplessiMap
	 *            the campiListaComplessiMap to set
	 */
	public void setCampiListaComplessiMap(HashMap<String, UnitDBModel> campiListaComplessiMap) {
		this.campiListaComplessiMap = campiListaComplessiMap;
		creaMappaListaCampiComplessi();
	}

	/**
	 * @return the condizione
	 */
	public String getCondizione() {
		return condizione;
	}

	/**
	 * @param condizione
	 *            the condizione to set
	 */
	public void setCondizione(String condizione) {
		this.condizione = condizione;
	}

	/**
	 * @return the campiSempliciMap
	 */
	public Map<String, SmEdizUnitVar> getCampiSempliciMap() {
		return campiSempliciMap;
	}

	/**
	 * @param campiSempliciMap
	 *            the campiSempliciMap to set
	 */
	public void setCampiSempliciMap(HashMap<String, SmEdizUnitVar> campiSempliciMap) {
		this.campiSempliciMap = campiSempliciMap;
	}

	/**
	 * @return the campiComplessiMap
	 */
	public Map<String, UnitDBModel> getCampiComplessiMap() {
		return campiComplessiMap;
	}

	/**
	 * @param campiComplessiMap
	 *            the campiComplessiMap to set
	 */
	public void setCampiComplessiMap(Map<String, UnitDBModel> campiComplessiMap) {
		this.campiComplessiMap = campiComplessiMap;
	}

	/**
	 * @return the campiSemplici
	 */
	public List<SmEdizUnitVar> getCampiSemplici() {
		return campiSemplici;
	}

	/**
	 * @param campiSemplici
	 *            the campiSemplici to set
	 */
	public void setCampiSemplici(List<SmEdizUnitVar> campiSemplici) {
		this.campiSemplici = campiSemplici;
		creaMappaCampiSemplici();
		aggiornaChiavi();
		aggiornaChiaviExt();
		aggiornaChiaviInt();
		aggiornaOrdinamento();
	}

	private void aggiornaOrdinamento() {
		// TODO Auto-generated method stub
		ordinamento = new TreeMap<String, String>();
		for (SmEdizUnitVar cella : campiSemplici) {
			if (cella.getSmEdizUnitVarModeldata().getFlagSort() == 1) {
				ordinamento.put(String.valueOf(cella.getOrdine()), cella.getSmVariable().getIdVariable());
			}
		}
	}

	private void aggiornaChiavi() {
		// TODO Auto-generated method stub
		chiave = new ArrayList<String>();
		for (SmEdizUnitVar cella : campiSemplici) {
			if (cella.getSmEdizUnitVarModeldata().getFlagChiave() == 1) {
				chiave.add(cella.getSmVariable().getIdVariable());
			}
		}
	}

	private void aggiornaChiaviExt() {
		// TODO Auto-generated method stub
		setChiaviExt(new ArrayList<String>());
		for (SmEdizUnitVar cella : campiSemplici) {
			if (cella.getSmEdizUnitVarModeldata().getFlagChiaveExt() == 1) {
				chiaviExt.add(cella.getSmVariable().getIdVariable());
			}
		}
	}

	private void aggiornaChiaviInt() {
		// TODO Auto-generated method stub
		setChiaviInt(new ArrayList<String>());
		for (SmEdizUnitVar cella : campiSemplici) {
			if (cella.getSmEdizUnitVarModeldata().getFlagChiaveInt() == 1) {
				chiaviInt.add(cella.getSmVariable().getIdVariable());
			}
		}
	}

	/**
	 * @return the ordinamento
	 */
	public Map<String, String> getOrdinamento() {
		return ordinamento;
	}

	/**
	 * @param ordinamento
	 *            the ordinamento to set
	 */
	public void setOrdinamento(Map<String, String> ordinamento) {
		this.ordinamento = ordinamento;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3495435188715855762L;

	private String indagine;
	private String etichetta;
	private String flagLista;
	private String flagOggetto;
	protected SmDataset tabellaPrincipale;
	protected List<SmDataset> tabelleSecondarie;

	/**
	 * @return the flagLista
	 */
	public String getFlagLista() {
		return flagLista;
	}

	/**
	 * @param flagLista
	 *            the flagLista to set
	 */
	public void setFlagLista(String flagLista) {
		this.flagLista = flagLista;
	}

	/**
	 * @return the flagOggetto
	 */
	public String getFlagOggetto() {
		return flagOggetto;
	}

	/**
	 * @param flagOggetto
	 *            the flagOggetto to set
	 */
	public void setFlagOggetto(String flagOggetto) {
		this.flagOggetto = flagOggetto;
	}

	private String codTipoModello;
	private String condizione;
	private List<String> chiave;
	private List<String> chiaviExt;

	/**
	 * @return the chiaviInt
	 */
	public List<String> getChiaviInt() {
		return chiaviInt;
	}

	/**
	 * @param chiaviInt
	 *            the chiaviInt to set
	 */
	public void setChiaviInt(List<String> chiaviInt) {
		this.chiaviInt = chiaviInt;
	}

	private List<String> chiaviInt;

	/**
	 * @return the chiave
	 */
	public List<String> getChiave() {
		return chiave;
	}

	private List<SmEdizUnitVar> campiSemplici;// campi primitivi
	private List<UnitDBModel> campiComplessi; // association
	private List<SmEdizUnitVar> campiListaSemplici;// campi primitivi
	private List<UnitDBModel> campiListaComplessi; // association

	private Map<String, String> ordinamento;
	private static final String ORDER_ASC = " asc";
	private static final String ORDER_DESC = " desc";

	private Map<String, SmEdizUnitVar> campiSempliciMap;
	private Map<String, SmEdizUnitVar> campiListaSempliciMap;
	private Map<String, UnitDBModel> campiComplessiMap;
	private Map<String, UnitDBModel> campiListaComplessiMap; // campi lista
																// di
																// oggetti

	public UnitDBModel() {
		super();
	}

	private void creaMappaCampiComplessi() {
		// TODO Auto-generated method stub
		campiComplessiMap = new HashMap<String, UnitDBModel>();
		if (campiComplessi != null) {
			for (UnitDBModel cella : campiComplessi) {
				String rigais = cella.getDescrizione();
				campiComplessiMap.put(rigais, cella);
			}
		}
	}

	private void creaMappaListaCampiComplessi() {
		// TODO Auto-generated method stub
		campiListaComplessiMap = new HashMap<String, UnitDBModel>();
		if (campiListaComplessi != null) {
			for (UnitDBModel cella : campiListaComplessi) {
				String rigais = cella.getDescrizione();
				campiListaComplessiMap.put(rigais, cella);
			}
		}
	}

	private void creaMappaCampiSemplici() {
		// TODO Auto-generated method stub
		campiSempliciMap = new TreeMap<String, SmEdizUnitVar>();
		if (campiSemplici != null) {
			for (SmEdizUnitVar cella : campiSemplici) {
				String rigais = cella.getSmVariable().getIdVariable();
				campiSempliciMap.put(rigais, cella);
			}
		}
	}

	private void creaMappaListaCampiSemplici() {
		// TODO Auto-generated method stub
		campiListaSempliciMap = new HashMap<String, SmEdizUnitVar>();
		if (campiListaSemplici != null) {
			for (SmEdizUnitVar cella : campiListaSemplici) {
				String rigais = cella.getSmVariable().getIdVariable();

				campiListaSempliciMap.put(rigais, cella);
			}
		}
	}

	public void setCampiComplessi(List<UnitDBModel> campiComplessi) {
		this.campiComplessi = campiComplessi;
		creaMappaCampiComplessi();
	}

	public List<UnitDBModel> getCampiComplessi() {
		return campiComplessi;
	}

	// Compare only account numbers
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitDBModel other = (UnitDBModel) obj;
		if (this.getId() != other.getId())
			return false;
		return true;
	}

	public void costruisciStringheDB() {

	}

	public String selectByChiave() {
		BEGIN(); // Clears ThreadLocal variable

		for (SmEdizUnitVar cella : campiSemplici) {
			SELECT(cella.getSmVariable().getIdVariable());

		}
		FROM(tabellaPrincipale.getTableName());
	     tabelleSecondarie.forEach(tabellas->{
	    	 JOIN( tabellas.getTableName()+ " ON " + tabellas.getTableName()+"."+tabellas.getTableKey()+" = " +tabellas.getTableMaster()+"."+tabellas.getKeyMaster());
	    	 
	     });
		
		for (String cella : chiave) {
			WHERE(cella + " = ? ");
		}
		for (String cella : ordinamento.values()) {
			ORDER_BY(cella);
		}
		return SQL();
	}

	public String selectAll() {
		BEGIN(); // Clears ThreadLocal variable

		for (SmEdizUnitVar cella : campiSemplici) {
			SELECT(cella.getSmVariable().getIdVariable());

		}
		FROM(tabellaPrincipale.getTableName());
	     tabelleSecondarie.forEach(tabellas->{
	    	 JOIN( tabellas.getTableName()+ " ON " + tabellas.getTableName()+"."+tabellas.getTableKey()+" = " +tabellas.getTableMaster()+"."+tabellas.getKeyMaster());
	    	 
	     });
	

		for (String cella : ordinamento.values()) {
			ORDER_BY(cella);
		}
		return SQL();
	}

	public String selectAllByChiaveExt() {
		// TODO Auto-generated method stub
		BEGIN(); // Clears ThreadLocal variable
		SELECT("*");
		FROM(tabellaPrincipale.getTableName());
	     tabelleSecondarie.forEach(tabellas->{
	    	 JOIN( tabellas.getTableName()+ " ON " + tabellas.getTableName()+"."+tabellas.getTableKey()+" = " +tabellas.getTableMaster()+"."+tabellas.getKeyMaster());
	    	 
	     });
	
		for (String cella : chiaviExt) {
			WHERE(cella + " = ? ");
		}
		if (condizione != null && condizione.trim().length() > 0) {
			WHERE(condizione);

		}
		for (String cella : ordinamento.values()) {
			ORDER_BY(cella);
		}
		return SQL();
	}

	public String selectAllWhere(List<String> condizioni) {
		BEGIN(); // Clears ThreadLocal variable
		SELECT("*");
		
		FROM(tabellaPrincipale.getTableName());
	     tabelleSecondarie.forEach(tabellas->{
	    	 JOIN( tabellas.getTableName()+ " ON " + tabellas.getTableName()+"."+tabellas.getTableKey()+" = " +tabellas.getTableMaster()+"."+tabellas.getKeyMaster());
	    	 
	     });
	
		
		for (String cella : condizioni) {
			WHERE(cella + " = ? ");
		}
		for (String cella : ordinamento.values()) {
			ORDER_BY(cella);
		}
		return SQL();
	}

	public String selectCampiWhere(List<String> campiSelect, List<String> condizioni) {
		BEGIN(); // Clears ThreadLocal variable

		 
		 
		for (String cella : campiSelect) {
			SELECT(cella);
		}
		
		FROM(tabellaPrincipale.getTableName());
	     tabelleSecondarie.forEach(tabellas->{
	    	 JOIN( tabellas.getTableName()+ " ON " + tabellas.getTableName()+"."+tabellas.getTableKey()+" = " +tabellas.getTableMaster()+"."+tabellas.getKeyMaster());
	    	 
	     });
	
	   
		for (String cella : condizioni) {
			WHERE(cella + " = ? ");
		}
		for (String cella : ordinamento.values()) {
			ORDER_BY(cella);
		}
		return SQL();
	}
	
	public String selectWithInQueryWhereListLR(List<String> campiSelect, List<LogicalRelation> listRL) {
		
		//Select * from ( select a1, ..an,b1,..bm from A join B) where ...
		BEGIN(); // Clears ThreadLocal variable

		 
		 
		for (String cella : campiSelect) {
			//SELECT_SUBQUERY(cella);
			SELECT(cella);
		}
		
		FROM(tabellaPrincipale.getTableName());
	     tabelleSecondarie.forEach(tabellas->{
	    	 JOIN( tabellas.getTableName()+ " ON " + tabellas.getTableName()+"."+tabellas.getTableKey()+" = " +tabellas.getTableMaster()+"."+tabellas.getKeyMaster());
	    	 
	     });
	
	   
		for (LogicalRelation cella : listRL) {
			WHERE(cella.getItem1() + " "+cella.getRelation()+ " ? ");
		}
		for (String cella : ordinamento.values()) {
			ORDER_BY(cella);
		}
		return SQL();
	}
	
	public String selectWithInQueryCampiWhere(List<String> campiSelect, List<String> condizioni) {
		
		//Select * from ( select a1, ..an,b1,..bm from A join B) where ...
		BEGIN(); // Clears ThreadLocal variable
 		for (String cella : campiSelect) {
			SELECT_SUBQUERY(cella);
		}
 	FROM(tabellaPrincipale.getTableName());
	     tabelleSecondarie.forEach(tabellas->{
	    	 JOIN( tabellas.getTableName()+ " ON " + tabellas.getTableName()+"."+tabellas.getTableKey()+" = " +tabellas.getTableMaster()+"."+tabellas.getKeyMaster());
	    	 
	     });
	
 		for (String cella : condizioni) {
			WHERE(cella + " = ? ");
		}
		for (String cella : ordinamento.values()) {
			ORDER_BY(cella);
		}
		return SQL();
	}

	
	public String selectCampiByCondizione(List<String> campiSelect, List<String> condizioni,
			List<String> campiOrdinamento) {
		BEGIN(); // Clears ThreadLocal variable

		for (String cella : campiSelect) {
			SELECT(cella);
		}

		FROM(tabellaPrincipale.getTableName());
	     tabelleSecondarie.forEach(tabellas->{
	    	 JOIN( tabellas.getTableName()+ " ON " + tabellas.getTableName()+"."+tabellas.getTableKey()+" = " +tabellas.getTableMaster()+"."+tabellas.getKeyMaster());
	    	 
	     });
	
	     
		for (String cella : condizioni) {
			WHERE(cella + " = ? ");
		}
		for (String cella : campiOrdinamento) {
			ORDER_BY(cella);
		}
		return SQL();
	}

	public String selectCampiByCondizioneRelazione(List<String> campiSelect, List<String> condizioni,
			List<GenericoDoppioBean> relazioni, List<String> campiOrdinamento) {
		BEGIN(); // Clears ThreadLocal variable

		for (String cella : campiSelect) {
			SELECT(cella);
		}

		FROM(tabellaPrincipale.getTableName());
	     tabelleSecondarie.forEach(tabellas->{
	    	 JOIN( tabellas.getTableName()+ " ON " + tabellas.getTableName()+"."+tabellas.getTableKey()+" = " +tabellas.getTableMaster()+"."+tabellas.getKeyMaster());
	    	 
	     });
	

		for (String cella : condizioni) {

			WHERE(cella + " = ? ");
		}
		for (GenericoDoppioBean cella : relazioni) {

			WHERE(cella.getCodice() + " " + cella.getParam1());
		}
		for (String cella : campiOrdinamento) {
			ORDER_BY(cella);
		}
		return SQL();
	}
	
	// costruisco la query per l'insert
	public String insertQuery() {
		BEGIN(); // Clears ThreadLocal variable
		INSERT_INTO(tabellaPrincipale.getTableName());
		
		for (SmEdizUnitVar cella : campiSemplici) {

			VALUES(cella.getSmVariable().getIdVariable(), "?");

		}

		return SQL();
	}
	/*
	// costruisco la query per l'update by chiave
	public String UpdateByChiave() {
		BEGIN(); // Clears ThreadLocal variable
		UPDATE(tabella);
		for (SmEdizUnitVar cella : campiSemplici) {
			if (cella.getSmEdizUnitVarModeldata().getFlagChiave() == 0) {
				SET(cella.getSmVariable().getIdVariable() + " = ? ");
			}
		}
		for (String cella : chiave) {
			WHERE(cella + " = ?");
		}

		return SQL();
	}
*/
	// costruisco la query per l'update by chiave
	public String UpdateTabellaCampiConChiave(String nomeTabella, List<String> campi, List<String> chiavi) {
		BEGIN(); // Clears ThreadLocal variable
		UPDATE(nomeTabella);
		for (String nomeCampo : campi) {

			SET(nomeCampo + " = ? ");

		}
		for (String nomeChiave : chiavi) {
			WHERE(nomeChiave + " = ?");
		}

		return SQL();
	}

	/*
	// costruisco la query per la delete by chiave
	public String DeleteByChiave() {
		BEGIN(); // Clears ThreadLocal variable
		DELETE_FROM(tabella);
		for (String cella : chiave) {
			WHERE(cella + " = ?");
		}

		return SQL();
	}
*/
	// restituoisco la lista dei campi che sono coinvolti nella UpdateByChiave
	public List<String> getNomeCampiInUpdateByChiave() {
		List<String> ret = new ArrayList<String>();
		for (SmEdizUnitVar cella : campiSemplici) {
			if (cella.getSmEdizUnitVarModeldata().getFlagChiave() == 0) {
				ret.add(cella.getSmVariable().getIdVariable());
			}
		}
		for (String k : chiave) {
			ret.add(k);
		}

		return ret;
	}

	// restituoisco la lista dei campi che sono coinvolti nella UpdateByChiave
	public List<String> getNomeCampiSemplici() {
		List<String> ret = new ArrayList<String>();
		for (SmEdizUnitVar cella : campiSemplici) {

			ret.add(cella.getSmVariable().getIdVariable());

		}

		return ret;
	}

	public String deletePersonSql() {
		BEGIN(); // Clears ThreadLocal variable
		DELETE_FROM("PERSON");
		WHERE("ID = ${id}");
		return SQL();
	}

	public String insertPersonSql() {
		BEGIN(); // Clears ThreadLocal variable
		INSERT_INTO("PERSON");
		VALUES("ID, FIRST_NAME", "${id}, ${firstName}");
		VALUES("LAST_NAME", "${lastName}");
		return SQL();
	}

	public String updatePersonSql() {
		BEGIN(); // Clears ThreadLocal variable
		UPDATE("PERSON");
		SET("FIRST_NAME = ${firstName}");
		WHERE("ID = ${id}");
		return SQL();
	}

	public void setChiaviExt(List<String> chiaviExt) {
		this.chiaviExt = chiaviExt;
	}

	public List<String> getChiaviExt() {
		return chiaviExt;
	}

	public void setCodTipoModello(String codTipoModello) {
		this.codTipoModello = codTipoModello;
	}

	public String getCodTipoModello() {
		return codTipoModello;
	}

	public String getEtichetta() {
		return etichetta;
	}

	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
	}

}
