-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: postgres
-- Source Schemata: postgres
-- Created: Fri Aug 14 14:19:06 2020
-- Workbench Version: 8.0.19
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema postgres
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `interpreter` ;
CREATE SCHEMA IF NOT EXISTS `interpreter` ;

-- ----------------------------------------------------------------------------
-- Table interpreter.fatt_portal
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`fatt_portal` (
  `keyid` BIGINT NULL,
  `utente` VARCHAR(240) NULL,
  `lastupdate` DATE NULL,
  `note_revisore` LONGTEXT NULL,
  `a01a` BIGINT NULL,
  `a01b` BIGINT NULL,
  `a01c` BIGINT NULL,
  `a01d` BIGINT NULL,
  `a01e` BIGINT NULL,
  `a011` LONGTEXT NULL,
  `a012` LONGTEXT NULL,
  `a013` LONGTEXT NULL,
  `a02` INT NULL,
  `nrinvio` SMALLINT NULL,
  `gxdb` SMALLINT NULL);

-- ----------------------------------------------------------------------------
-- Table interpreter.fatt_sintesi
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`fatt_sintesi` (
  `keyid` BIGINT NULL,
  `anno` SMALLINT NULL,
  `mese` SMALLINT NULL,
  `asia` BIGINT NULL,
  `cod_ditt` LONGTEXT NULL,
  `uni_funz` SMALLINT NULL,
  `data_ins` DATE NULL,
  `utente` LONGTEXT NULL,
  `nrinvio` SMALLINT NULL,
  `note_revisore` LONGTEXT NULL,
  `naz_fatt` BIGINT NULL,
  `est_fatt` BIGINT NULL,
  `cee_fatt` BIGINT NULL,
  `naz_ord` BIGINT NULL,
  `est_ord` BIGINT NULL,
  `nota_fn` LONGTEXT NULL,
  `nota_fe` LONGTEXT NULL,
  `nota_fz` LONGTEXT NULL,
  `e_fatt` BIGINT NULL,
  `flag` SMALLINT NULL);

-- ----------------------------------------------------------------------------
-- Table interpreter.mi_fatt_portal
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`mi_fatt_portal` (
  `keyid` BIGINT NULL,
  `cod_asia` DECIMAL(22,0) NULL,
  `indagine` VARCHAR(72) NOT NULL,
  `ur` VARCHAR(48) NOT NULL,
  `keydata` INT NOT NULL,
  `flag` DECIMAL(22,0) NULL,
  `percorso` LONGTEXT NULL,
  `percdom` LONGTEXT NULL,
  `percreg` LONGTEXT NULL,
  `gruppo_impresa` VARCHAR(36) NULL,
  `lastupdate` DATE NULL,
  `lastbk` DATE NULL,
  `codrisposta` VARCHAR(6) NULL,
  `attivo` SMALLINT NULL,
  `datains` DATE NULL,
  `utente` VARCHAR(240) NULL,
  `nrinvio` SMALLINT NULL);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_business_function
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_business_function` (
  `id` DECIMAL(22,0) NOT NULL,
  `nome` VARCHAR(50) NULL,
  `descr` VARCHAR(100) NULL,
  `etichetta` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_business_function_old
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_business_function_old` (
  `id` DECIMAL(22,0) NOT NULL,
  `descrizione` VARCHAR(250) NULL,
  `process_step` DECIMAL(22,0) NULL,
  `codice` VARCHAR(20) NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_business_process
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_business_process` (
  `id` DECIMAL(22,0) NOT NULL,
  `nome` VARCHAR(50) NULL,
  `descr` VARCHAR(100) NULL,
  `etichetta` VARCHAR(100) NULL,
  `regole` DECIMAL(22,0) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_business_process_regole_fkey`
    FOREIGN KEY (`regole`)
    REFERENCES `interpreter`.`sm_ruleset` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_business_step
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_business_step` (
  `id` DECIMAL(22,0) NOT NULL,
  `nome` VARCHAR(50) NULL,
  `descr` VARCHAR(100) NULL,
  `regola` DECIMAL(22,0) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_business_step_regola_fkey`
    FOREIGN KEY (`regola`)
    REFERENCES `interpreter`.`sm_ruleset` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_campione
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_campione` (
  `id` DECIMAL(22,0) NOT NULL,
  `descrizione` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_dataset
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_dataset` (
  `id` DECIMAL(22,0) NOT NULL,
  `tabella` VARCHAR(100) NOT NULL,
  `tipo_tabella` DECIMAL(22,0) NULL,
  `unit` DECIMAL(22,0) NULL,
  `table_name` VARCHAR(100) NULL,
  `table_key` VARCHAR(100) NULL,
  `table_master` VARCHAR(100) NULL,
  `key_master` VARCHAR(100) NULL,
  UNIQUE INDEX `sm_dataset_id_key` (`id` ASC),
  CONSTRAINT `sm_dataset_tipo_tabella_fkey`
    FOREIGN KEY (`tipo_tabella`)
    REFERENCES `interpreter`.`sm_tipo_tabella` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_dataset_unit_fkey`
    FOREIGN KEY (`unit`)
    REFERENCES `interpreter`.`sm_unit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_ediz_unit_var
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_ediz_unit_var` (
  `id` DECIMAL(22,0) NOT NULL,
  `variabile` DECIMAL(22,0) NULL,
  `ordine` SMALLINT NULL,
  `unit` DECIMAL(22,0) NULL,
  `alias_sql` VARCHAR(100) NULL,
  `attiva` SMALLINT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_ediz_unit_var_unit_fkey`
    FOREIGN KEY (`unit`)
    REFERENCES `interpreter`.`sm_unit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_ediz_unit_var_variabile_fkey`
    FOREIGN KEY (`variabile`)
    REFERENCES `interpreter`.`sm_variable` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_ediz_unit_var_modeldata
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_ediz_unit_var_modeldata` (
  `flag_lista` SMALLINT NULL,
  `flag_chiave` SMALLINT NULL,
  `flag_sort` SMALLINT NULL,
  `flag_fisso` SMALLINT NULL,
  `flag_chiave_ext` SMALLINT NULL,
  `flag_chiave_int` SMALLINT NULL,
  `flag_attivo` SMALLINT NULL,
  `flag_oggetto` SMALLINT NULL,
  `condizione` LONGTEXT NULL,
  `sm_ediz_uv_id` DECIMAL(22,0) NOT NULL,
  PRIMARY KEY (`sm_ediz_uv_id`),
  CONSTRAINT `sm_ediz_unit_var_modeldata_sm_ediz_uv_id_fkey`
    FOREIGN KEY (`sm_ediz_uv_id`)
    REFERENCES `interpreter`.`sm_ediz_unit_var` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_edizione
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_edizione` (
  `id` DECIMAL(22,0) NOT NULL,
  `codice_edizione` VARCHAR(50) NOT NULL,
  `nome_edizione` VARCHAR(100) NULL,
  `data_in` DATE NULL,
  `data_fin` DATE NULL,
  `indagine` DECIMAL(21,0) NOT NULL,
  `periodicita` DECIMAL(22,0) NULL,
  `tipo` DECIMAL(22,0) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_edizione_indagine_fkey`
    FOREIGN KEY (`indagine`)
    REFERENCES `interpreter`.`sm_indagine` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_edizione_periodicita_fkey`
    FOREIGN KEY (`periodicita`)
    REFERENCES `interpreter`.`sm_periodicita` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_edizione_tipo_fkey`
    FOREIGN KEY (`tipo`)
    REFERENCES `interpreter`.`sm_edizione_tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_edizione_campione
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_edizione_campione` (
  `id` DECIMAL(22,0) NOT NULL,
  `tabella` VARCHAR(100) NULL,
  `campione` DECIMAL(22,0) NOT NULL,
  `edizione` DECIMAL(22,0) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_edizione_campione_campione_fkey`
    FOREIGN KEY (`campione`)
    REFERENCES `interpreter`.`sm_campione` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_edizione_tipo
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_edizione_tipo` (
  `id` DECIMAL(22,0) NOT NULL,
  `descrizione` VARCHAR(50) NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_fase
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_fase` (
  `id` DECIMAL(22,0) NOT NULL,
  `nome` VARCHAR(50) NULL,
  `descr` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_indagine
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_indagine` (
  `id` DECIMAL(22,0) NOT NULL,
  `codice_processo_sidi` VARCHAR(21) NOT NULL,
  `nome_sidi` LONGTEXT NOT NULL,
  `nome_sidi_eng` LONGTEXT NULL,
  `descrizione` LONGTEXT NULL,
  `descrizione_eng` LONGTEXT NULL,
  `email_responsabile` LONGTEXT NULL,
  `nome_responsabile` LONGTEXT NULL,
  `tipo_processo` SMALLINT NULL,
  `nome_tipo_processo` LONGTEXT NOT NULL,
  `stato` VARCHAR(21) NULL,
  `period_ciclo_prod` VARCHAR(90) NULL,
  `period_ciclo_prod_eng` VARCHAR(90) NULL,
  `nome_disegno` LONGTEXT NULL,
  `nome_disegno_eng` LONGTEXT NULL,
  `codice_disegno` INT NULL,
  `sigla` VARCHAR(150) NULL,
  `normativa` LONGTEXT NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_indagine_fasi_sidi
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_indagine_fasi_sidi` (
  `id` DECIMAL(22,0) NOT NULL,
  `indagine` DECIMAL(22,0) NULL,
  `codice_processo_sidi` VARCHAR(21) NOT NULL,
  `codice_fase` BIGINT NOT NULL,
  `codice_fase_padre` BIGINT NULL,
  `nome_fase` LONGTEXT NOT NULL,
  `nome_fase_eng` LONGTEXT NULL,
  `descrizione_fase` LONGTEXT NULL,
  `descrizione_fase_eng` LONGTEXT NULL,
  `stato_fase` VARCHAR(39) NULL,
  `ordine0` BIGINT NULL,
  `ordine1` BIGINT NULL,
  `ordine2` BIGINT NULL,
  `ordine3` BIGINT NULL,
  `data_in` DATE NULL,
  `data_fin` DATE NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_indagine_fasi_sidi_indagine_fkey`
    FOREIGN KEY (`indagine`)
    REFERENCES `interpreter`.`sm_indagine` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_indagine_fenomeni
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_indagine_fenomeni` (
  `id` DECIMAL(22,0) NOT NULL,
  `indagine` DECIMAL(22,0) NULL,
  `codice_processo_sidi` VARCHAR(21) NOT NULL,
  `codice_fenomeno` BIGINT NOT NULL,
  `nome_fenomeno` LONGTEXT NOT NULL,
  `nome_fenomeno_eng` LONGTEXT NULL,
  `descrizione_fen` LONGTEXT NULL,
  `descrizione_fen_eng` LONGTEXT NULL,
  `stato_fen` VARCHAR(39) NULL,
  `data_in` DATE NOT NULL,
  `data_fin` DATE NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_indagine_fenomeni_indagine_fkey`
    FOREIGN KEY (`indagine`)
    REFERENCES `interpreter`.`sm_indagine` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_indagine_lista_utenti
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_indagine_lista_utenti` (
  `id` DECIMAL(22,0) NOT NULL,
  `tabella` VARCHAR(50) NULL,
  `indagine` DECIMAL(22,0) NULL,
  PRIMARY KEY (`id`),
  INDEX `sm_indagine_lista_utenti_ix_relationship26` (`indagine` ASC) VISIBLE,
  CONSTRAINT `sm_indagine_lista_utenti_indagine_fkey`
    FOREIGN KEY (`indagine`)
    REFERENCES `interpreter`.`sm_indagine` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_indagine_psn
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_indagine_psn` (
  `id` DECIMAL(22,0) NOT NULL,
  `indagine` DECIMAL(22,0) NULL,
  `codice_processo_sidi` VARCHAR(21) NOT NULL,
  `anno_rif_da` SMALLINT NOT NULL,
  `codice_psn` VARCHAR(147) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_indagine_psn_indagine_fkey`
    FOREIGN KEY (`indagine`)
    REFERENCES `interpreter`.`sm_indagine` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_indagine_unita_ril
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_indagine_unita_ril` (
  `id` DECIMAL(22,0) NOT NULL,
  `indagine` DECIMAL(22,0) NULL,
  `unit_type` DECIMAL(22,0) NULL,
  `codice_processo_sidi` VARCHAR(21) NOT NULL,
  `codice_unita` BIGINT NOT NULL,
  `nome_unita` LONGTEXT NOT NULL,
  `nome_unita_eng` LONGTEXT NULL,
  `descrizione_unita` LONGTEXT NULL,
  `descrizione_unita_eng` LONGTEXT NULL,
  `stato_unita` VARCHAR(39) NULL,
  `data_in` DATE NULL,
  `data_fin` DATE NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_indagine_unita_ril_indagine_fkey`
    FOREIGN KEY (`indagine`)
    REFERENCES `interpreter`.`sm_indagine` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_indagine_unita_ril_unit_type_fkey`
    FOREIGN KEY (`unit_type`)
    REFERENCES `interpreter`.`sm_unit_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_instance_variable
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_instance_variable` (
  `id` DECIMAL(20,0) NOT NULL,
  `nome_campo` VARCHAR(50) NOT NULL,
  `descrizione` VARCHAR(100) NULL,
  `etichetta` VARCHAR(50) NULL,
  `tipo` VARCHAR(30) NULL,
  `inf` VARCHAR(30) NULL,
  `sup` VARCHAR(30) NULL,
  `miss` VARCHAR(30) NULL,
  `indagine` VARCHAR(10) NULL,
  `length` BIGINT NULL,
  `tipo_variabile_sum` SMALLINT NULL,
  `id_variable` DECIMAL(22,0) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_instance_variable_id_variable_fkey`
    FOREIGN KEY (`id_variable`)
    REFERENCES `interpreter`.`sm_variable` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_link_edizione_function
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_link_edizione_function` (
  `edizione` DECIMAL(22,0) NULL,
  `funzione` DECIMAL(22,0) NULL,
  `quando` DATE NULL,
  `attiva` DECIMAL(22,0) NULL,
  `eseguita` DECIMAL(22,0) NULL,
  `errore` DECIMAL(22,0) NULL,
  `utente` DECIMAL(22,0) NULL,
  CONSTRAINT `sm_link_edizione_function_edizione_fkey`
    FOREIGN KEY (`edizione`)
    REFERENCES `interpreter`.`sm_business_function` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_link_edizione_function_funzione_fkey`
    FOREIGN KEY (`funzione`)
    REFERENCES `interpreter`.`sm_edizione` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_link_function_process
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_link_function_process` (
  `bfunction` DECIMAL(22,0) NOT NULL,
  `bprocess` DECIMAL(22,0) NOT NULL,
  PRIMARY KEY (`bprocess`, `bfunction`),
  CONSTRAINT `sm_link_function_process_bfunction_fkey`
    FOREIGN KEY (`bfunction`)
    REFERENCES `interpreter`.`sm_business_function` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_link_function_process_bprocess_fkey`
    FOREIGN KEY (`bprocess`)
    REFERENCES `interpreter`.`sm_business_process` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_link_process_step
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_link_process_step` (
  `bstep` DECIMAL(22,0) NOT NULL,
  `bprocess` DECIMAL(22,0) NOT NULL,
  PRIMARY KEY (`bstep`, `bprocess`),
  CONSTRAINT `sm_link_process_step_bprocess_fkey`
    FOREIGN KEY (`bprocess`)
    REFERENCES `interpreter`.`sm_business_process` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_link_process_step_bstep_fkey`
    FOREIGN KEY (`bstep`)
    REFERENCES `interpreter`.`sm_business_step` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_link_step_instance
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_link_step_instance` (
  `istanza` DECIMAL(22,0) NULL,
  `step` DECIMAL(22,0) NULL,
  CONSTRAINT `sm_link_step_instance_istanza_fkey`
    FOREIGN KEY (`istanza`)
    REFERENCES `interpreter`.`sm_step_instance` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_link_step_instance_step_fkey`
    FOREIGN KEY (`step`)
    REFERENCES `interpreter`.`sm_business_step` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_parametri
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_parametri` (
  `id` DECIMAL(22,0) NOT NULL,
  `nome` VARCHAR(200) NULL,
  `descr` LONGTEXT NULL,
  `valore` VARCHAR(200) NULL,
  `tipo` DECIMAL(22,0) NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_periodicita
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_periodicita` (
  `id` DECIMAL(22,0) NOT NULL,
  `descrizione` VARCHAR(50) NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_population
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_population` (
  `id` DECIMAL(22,0) NOT NULL,
  `descrizione` VARCHAR(100) NULL,
  `edizione` DECIMAL(22,0) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_population_edizione_fkey`
    FOREIGN KEY (`edizione`)
    REFERENCES `interpreter`.`sm_edizione` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_rule
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_rule` (
  `id` DECIMAL(22,0) NOT NULL,
  `code` VARCHAR(50) NULL,
  `descr` VARCHAR(200) NULL,
  `rtype` DECIMAL(22,0) NULL,
  `errcode` DECIMAL(22,0) NULL,
  `active` DECIMAL(22,0) NULL,
  `formula` LONGTEXT NULL,
  `ruleset` DECIMAL(22,0) NULL,
  `variabile` VARCHAR(50) NULL,
  `formula_ori` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_rule_ruleset_fkey`
    FOREIGN KEY (`ruleset`)
    REFERENCES `interpreter`.`sm_ruleset` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_ruleset
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_ruleset` (
  `id` DECIMAL(22,0) NOT NULL,
  `nome` VARCHAR(50) NULL,
  `descr` VARCHAR(50) NULL,
  `tipo` DECIMAL(22,0) NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_step_instance
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_step_instance` (
  `id` DECIMAL(22,0) NOT NULL,
  `nome` VARCHAR(50) NULL,
  `descr` VARCHAR(100) NULL,
  `fcall` VARCHAR(50) NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_tipo_oggetto
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_tipo_oggetto` (
  `id` DECIMAL(22,0) NOT NULL,
  `nome` VARCHAR(128) NULL,
  `descr` LONGTEXT NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_tipo_tabella
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_tipo_tabella` (
  `id` DECIMAL(22,0) NOT NULL,
  `descrizione` VARCHAR(50) NULL,
  `flag_backup` SMALLINT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_unit
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_unit` (
  `id` DECIMAL(22,0) NOT NULL,
  `descrizione` VARCHAR(100) NULL,
  `unit_type` DECIMAL(22,0) NOT NULL,
  `unit_padre` DECIMAL(22,0) NULL,
  PRIMARY KEY (`id`),
  INDEX `sm_unit_unit_sel_pk` (`unit_padre` ASC) VISIBLE,
  CONSTRAINT `sm_unit_unit_padre_fkey`
    FOREIGN KEY (`unit_padre`)
    REFERENCES `interpreter`.`sm_unit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_unit_unit_type_fkey`
    FOREIGN KEY (`unit_type`)
    REFERENCES `interpreter`.`sm_unit_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_unit_edition_fase
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_unit_edition_fase` (
  `id` DECIMAL(22,0) NOT NULL,
  `nome` VARCHAR(50) NULL,
  `descr` VARCHAR(100) NULL,
  `edizione` DECIMAL(22,0) NULL,
  `fase` DECIMAL(22,0) NULL,
  `unit` DECIMAL(22,0) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_unit_edition_fase_edizione_fkey`
    FOREIGN KEY (`edizione`)
    REFERENCES `interpreter`.`sm_edizione` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_unit_edition_fase_fase_fkey`
    FOREIGN KEY (`fase`)
    REFERENCES `interpreter`.`sm_fase` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_unit_edition_fase_unit_fkey`
    FOREIGN KEY (`unit`)
    REFERENCES `interpreter`.`sm_unit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_unit_join
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_unit_join` (
  `id` DECIMAL(22,0) NULL,
  `process` DECIMAL(22,0) NULL,
  `unit` VARCHAR(128) NULL,
  `table_master` VARCHAR(128) NULL,
  `key_master` VARCHAR(128) NULL,
  `table_name` VARCHAR(128) NULL,
  `key_name` VARCHAR(128) NULL,
  `dblink` VARCHAR(200) NULL,
  `where_stm` LONGTEXT NULL);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_unit_select
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_unit_select` (
  `process` DECIMAL(22,0) NULL,
  `table_name` VARCHAR(128) NULL,
  `variabile` LONGTEXT NULL,
  `col_name` VARCHAR(128) NULL);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_unit_type
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_unit_type` (
  `id` DECIMAL(22,0) NOT NULL,
  `id_unit_type` VARCHAR(150) NOT NULL,
  `descrizione_it` LONGTEXT NOT NULL,
  `descrizione_en` LONGTEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `sm_unit_type_id_unit_type_key` (`id_unit_type` ASC) VISIBLE);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_variabile_type
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_variabile_type` (
  `id` DECIMAL(22,0) NOT NULL,
  `nome_tipo_variabile_ita` LONGTEXT NULL,
  `nome_tipo_variabile_eng` LONGTEXT NULL,
  `tipo_variabile` VARCHAR(6) NOT NULL,
  `titolo` LONGTEXT NULL,
  `ordine` SMALLINT NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_variable
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_variable` (
  `id` DECIMAL(22,0) NOT NULL,
  `id_variable` VARCHAR(150) NOT NULL,
  `nome_variable_it` LONGTEXT NOT NULL,
  `nome_variable_en` LONGTEXT NULL,
  `definizione_it` LONGTEXT NULL,
  `definizione_en` LONGTEXT NULL,
  `raggruppamento` VARCHAR(150) NULL,
  `tipo_variabile` VARCHAR(6) NOT NULL,
  `tipo_variabile_id` DECIMAL(22,0) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_variable_tipo_variabile_id_fkey`
    FOREIGN KEY (`tipo_variabile_id`)
    REFERENCES `interpreter`.`sm_variabile_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_workflow
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_workflow` (
  `step` DECIMAL(22,0) NULL,
  `active` DECIMAL(22,0) NULL,
  `regola` DECIMAL(22,0) NULL,
  `action` DECIMAL(22,0) NULL,
  `eccezione` DECIMAL(22,0) NULL,
  `livello` DECIMAL(22,0) NULL,
  CONSTRAINT `sm_workflow_action_fkey`
    FOREIGN KEY (`action`)
    REFERENCES `interpreter`.`sm_business_step` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_workflow_eccezione_fkey`
    FOREIGN KEY (`eccezione`)
    REFERENCES `interpreter`.`sm_business_step` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_workflow_regola_fkey`
    FOREIGN KEY (`regola`)
    REFERENCES `interpreter`.`sm_rule` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_workflow_step_fkey`
    FOREIGN KEY (`step`)
    REFERENCES `interpreter`.`sm_business_step` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.sm_workset
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`sm_workset` (
  `id` DECIMAL(22,0) NOT NULL,
  `descrizione` LONGTEXT NULL,
  `id_oggetto` DECIMAL(22,0) NULL,
  `tipo_oggetto` DECIMAL(22,0) NULL,
  `tipo_io` DECIMAL(22,0) NULL,
  `istanza` DECIMAL(22,0) NULL,
  `ordine` DECIMAL(22,0) NOT NULL,
  `nome_oggetto` VARCHAR(16) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sm_workset_istanza_fkey`
    FOREIGN KEY (`istanza`)
    REFERENCES `interpreter`.`sm_step_instance` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sm_workset_tipo_oggetto_fkey`
    FOREIGN KEY (`tipo_oggetto`)
    REFERENCES `interpreter`.`sm_tipo_oggetto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.tipo_variabile_sum
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`tipo_variabile_sum` (
  `nome_tipo_variabile_ita` LONGTEXT NULL,
  `nome_tipo_variabile_eng` LONGTEXT NULL,
  `tipo_variabile` VARCHAR(6) NOT NULL,
  `titolo` LONGTEXT NULL,
  `ordine` SMALLINT NULL);

-- ----------------------------------------------------------------------------
-- Table interpreter.wd_edizione
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`wd_edizione` (
  `id` DECIMAL(22,0) NOT NULL,
  `anno` SMALLINT NOT NULL,
  `mese` SMALLINT NOT NULL,
  `user_lavorazione` DECIMAL(22,0) NULL,
  `data_ultima_lavorazione` DATE NULL,
  `tipo` VARCHAR(100) NULL,
  `indagine` VARCHAR(100) NULL,
  `user_ultima_lavorazione` VARCHAR(100) NULL,
  `data_inizio` DATE NULL,
  `data_fine` DATE NULL,
  `periodicita` SMALLINT NULL,
  `edizione` DECIMAL(22,0) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `wd_edizione_ind_edi` (`edizione` ASC) VISIBLE,
  CONSTRAINT `wd_edizione_edizione_fkey`
    FOREIGN KEY (`edizione`)
    REFERENCES `interpreter`.`sm_edizione` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table interpreter.wd_edizione_old
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `interpreter`.`wd_edizione_old` (
  `id` DECIMAL(22,0) NOT NULL,
  `edizione` DECIMAL(22,0) NOT NULL,
  `mese` DECIMAL(22,0) NULL,
  `anno` DECIMAL(22,0) NULL,
  `periodicita` DECIMAL(22,0) NOT NULL,
  PRIMARY KEY (`id`));
SET FOREIGN_KEY_CHECKS = 1;
