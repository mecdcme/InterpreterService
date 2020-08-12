package it.istat.sintesi.interpreter.utils;

public interface Interpreter_CONST {

	public final static String FALSE = "false";
	public final static String TRUE = "true";

	// Gestione Indagini
	public final static String INDAGINE_SELEZIONATA = "indagineSelezionata";

	// Gestione Regole
	public final static String ERRORE_VALIDAZIONE_REGOLA = "Errore interpretazione regola";
	public final static String VALIDAZIONE_REGOLA_NOK = "Errore regola non validata";
	public final static String VALIDAZIONE_REGOLA_OK = "Regola validata OK";
	public final static String ERRORE_VALIDAZIONE_CONDIZIONE = "Errore interpretazione condizione";
	public final static String VALIDAZIONE_NO_CONDIZIONE = "NO Condizione Regola OK";
	public final static String LISTA_REGOLE_OK = "listaregoleok";
	public final static String LISTA_REGOLE_KO = "listaregoleko";
	public final static String KO = "ko";
	public final static String OK = "ok";

	// Gestione Questionari
	public static String NAMESPACE_POJO = "it.istat.mec.sintesi.pojo.Pojo";

	public final static String QUESTIONARIO_BEAN = "questionariobean";
	public final static String QUESTIONARIO_MODIFICATO = "qmodificato";

	public final static String STATO_BEAN = "statoBean";
	public final static String STATO_BEAN_NUOVO = "N";
	public final static String STATO_BEAN_MODIFICATO = "M";
	public final static String STATO_BEAN_CANCELLATO = "C";
	public final static String STATO_BEAN_INVARIATO = "I";
	public final static String ECCEZIONE_ACCESSO_METODO = "Impossibile accedere a metodo: ";
	public static final String EDIZIONEI_WF = "edizioneindaginewf";
	public static final String WS_INPUT_SELECTION_RULES = "wsRuleInputSelection";
	public static final String WS_INPUT_UNIT = "wsInputUnit";
	public static final String WS_INPUT_VARIABLE = "wsInputVar";
	public static final String WS_TRANSFORMATION_RULES = "wsTransformationRules";
	public static final String WS_OUTPUT_UNIT= "wsOutputUnit";
	public static final Short TIPO_IO_INPUT = 1;
	public static final Short TIPO_IO_OUTPUT= 2;
	public static final Object WS_VALIDATION_RULES= "wsValidationnRules";

}
