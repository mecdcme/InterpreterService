drop schema if exists interpreter cascade;
create schema interpreter;
--
-- table structure for table `sm_business_function`
--

create table "sm_business_function" (  "id" numeric(22,0) not null ,
  "nome" varchar(50) null ,
  "descr" varchar(100) null ,
  "etichetta" varchar(100) null ,
  primary key ("id")
); 


--
-- table structure for table `sm_business_function_old`
--

create table "sm_business_function_old" (  "id" numeric(22,0) not null ,
  "descrizione" varchar(250) null ,
  "process_step" numeric(22,0) null ,
  "codice" varchar(20) null ,
  primary key ("id")
); 


--
-- table structure for table `sm_ruleset`
--

create table "sm_ruleset" (  "id" numeric(22,0) not null ,
  "nome" varchar(50) null ,
  "descr" varchar(50) null ,
  "tipo" numeric(22,0) null ,
  primary key ("id")
); 


--
-- table structure for table `sm_business_step`
--

create table "sm_business_step" (  "id" numeric(22,0) not null ,
  "nome" varchar(50) null ,
  "descr" varchar(100) null ,
  "regola" numeric(22,0) null ,
  primary key ("id"),foreign key ("regola") references "sm_ruleset" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_campione`
--

create table "sm_campione" (  "id" numeric(22,0) not null ,
  "descrizione" varchar(100) null ,
  primary key ("id")
); 


--
-- table structure for table `sm_tipo_tabella`
--

create table "sm_tipo_tabella" (  "id" numeric(22,0) not null ,
  "descrizione" varchar(50) null ,
  "flag_backup" smallint null default 0,
  primary key ("id")
); 


--
-- table structure for table `sm_edizione_tipo`
--

create table "sm_edizione_tipo" (  "id" numeric(22,0) not null ,
  "descrizione" varchar(50) null ,
  primary key ("id")
); 


--
-- table structure for table `sm_edizione_campione`
--

create table "sm_edizione_campione" (  "id" numeric(22,0) not null ,
  "tabella" varchar(100) null ,
  "campione" numeric(22,0) not null ,
  "edizione" numeric(22,0) not null ,
  primary key ("id"),foreign key ("campione") references "sm_campione" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_fase`
--

create table "sm_fase" (  "id" numeric(22,0) not null ,
  "nome" varchar(50) null ,
  "descr" varchar(100) null ,
  primary key ("id")
); 


--
-- table structure for table `sm_unit_type`
--

create table "sm_unit_type" (  "id" numeric(22,0) not null ,
  "id_unit_type" varchar(150) not null ,
  "descrizione_it" text not null ,
  "descrizione_en" text null ,
  primary key ("id"),
  unique ("id_unit_type")
); 


--
-- table structure for table `sm_unit`
--

create table "sm_unit" (  "id" numeric(22,0) not null ,
  "descrizione" varchar(100) null ,
  "unit_type" numeric(22,0) not null ,
  "unit_padre" numeric(22,0) null ,
  primary key ("id"),foreign key ("unit_type") references "sm_unit_type" ( "id" ) on update no action on delete no action,foreign key ("unit_padre") references "sm_unit" ( "id" ) on update no action on delete no action
); 
create index "sm_unit_unit_sel_pk" on "sm_unit" ("unit_padre");


--
-- table structure for table `sm_indagine`
--

create table "sm_indagine" (  "id" numeric(22,0) not null ,
  "codice_processo_sidi" varchar(21) not null ,
  "nome_sidi" text not null ,
  "nome_sidi_eng" text null ,
  "descrizione" text null ,
  "descrizione_eng" text null ,
  "email_responsabile" text null ,
  "nome_responsabile" text null ,
  "tipo_processo" smallint null ,
  "nome_tipo_processo" text not null ,
  "stato" varchar(21) null ,
  "period_ciclo_prod" varchar(90) null ,
  "period_ciclo_prod_eng" varchar(90) null ,
  "nome_disegno" text null ,
  "nome_disegno_eng" text null ,
  "codice_disegno" integer null ,
  "sigla" varchar(150) null ,
  "normativa" text null ,
  primary key ("id")
); 


--
-- table structure for table `sm_indagine_fasi_sidi`
--

create table "sm_indagine_fasi_sidi" (  "id" numeric(22,0) not null ,
  "indagine" numeric(22,0) null ,
  "codice_processo_sidi" varchar(21) not null ,
  "codice_fase" bigint not null ,
  "codice_fase_padre" bigint null ,
  "nome_fase" text not null ,
  "nome_fase_eng" text null ,
  "descrizione_fase" text null ,
  "descrizione_fase_eng" text null ,
  "stato_fase" varchar(39) null ,
  "ordine0" bigint null ,
  "ordine1" bigint null ,
  "ordine2" bigint null ,
  "ordine3" bigint null ,
  "data_in" date null ,
  "data_fin" date null ,
  primary key ("id"),foreign key ("indagine") references "sm_indagine" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_indagine_fenomeni`
--

create table "sm_indagine_fenomeni" (  "id" numeric(22,0) not null ,
  "indagine" numeric(22,0) null ,
  "codice_processo_sidi" varchar(21) not null ,
  "codice_fenomeno" bigint not null ,
  "nome_fenomeno" text not null ,
  "nome_fenomeno_eng" text null ,
  "descrizione_fen" text null ,
  "descrizione_fen_eng" text null ,
  "stato_fen" varchar(39) null ,
  "data_in" date not null ,
  "data_fin" date not null ,
  primary key ("id"),foreign key ("indagine") references "sm_indagine" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_indagine_lista_utenti`
--

create table "sm_indagine_lista_utenti" (  "id" numeric(22,0) not null ,
  "tabella" varchar(50) null ,
  "indagine" numeric(22,0) null ,
  primary key ("id"),foreign key ("indagine") references "sm_indagine" ( "id" ) on update no action on delete no action
); 
create index "sm_indagine_lista_utenti_ix_relationship26" on "sm_indagine_lista_utenti" ("indagine");


--
-- table structure for table `sm_indagine_psn`
--

create table "sm_indagine_psn" (  "id" numeric(22,0) not null ,
  "indagine" numeric(22,0) null ,
  "codice_processo_sidi" varchar(21) not null ,
  "anno_rif_da" smallint not null ,
  "codice_psn" varchar(147) null ,
  primary key ("id"),foreign key ("indagine") references "sm_indagine" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_indagine_unita_ril`
--

create table "sm_indagine_unita_ril" (  "id" numeric(22,0) not null ,
  "indagine" numeric(22,0) null ,
  "unit_type" numeric(22,0) null ,
  "codice_processo_sidi" varchar(21) not null ,
  "codice_unita" bigint not null ,
  "nome_unita" text not null ,
  "nome_unita_eng" text null ,
  "descrizione_unita" text null ,
  "descrizione_unita_eng" text null ,
  "stato_unita" varchar(39) null ,
  "data_in" date null ,
  "data_fin" date null ,
  primary key ("id"),foreign key ("indagine") references "sm_indagine" ( "id" ) on update no action on delete no action,foreign key ("unit_type") references "sm_unit_type" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_periodicita`
--

create table "sm_periodicita" (  "id" numeric(22,0) not null ,
  "descrizione" varchar(50) null ,
  primary key ("id")
); 


--
-- table structure for table `sm_variabile_type`
--

create table "sm_variabile_type" (  "id" numeric(22,0) not null ,
  "nome_tipo_variabile_ita" text null ,
  "nome_tipo_variabile_eng" text null ,
  "tipo_variabile" varchar(6) not null ,
  "titolo" text null ,
  "ordine" smallint null ,
  primary key ("id")
); 


--
-- table structure for table `sm_edizione`
--

create table "sm_edizione" (  "id" numeric(22,0) not null ,
  "codice_edizione" varchar(50) not null ,
  "nome_edizione" varchar(100) null ,
  "data_in" date null ,
  "data_fin" date null ,
  "indagine" numeric(21,0) not null ,
  "periodicita" numeric(22,0) null ,
  "tipo" numeric(22,0) null ,
  primary key ("id"),foreign key ("tipo") references "sm_edizione_tipo" ( "id" ) on update no action on delete no action,foreign key ("indagine") references "sm_indagine" ( "id" ) on update no action on delete no action,foreign key ("periodicita") references "sm_periodicita" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_business_process`
--

create table "sm_business_process" (  "id" numeric(22,0) not null ,
  "nome" varchar(50) null ,
  "descr" varchar(100) null ,
  "etichetta" varchar(100) null ,
  "regole" numeric(22,0) null ,
  primary key ("id"),foreign key ("regole") references "sm_ruleset" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_step_instance`
--

create table "sm_step_instance" (  "id" numeric(22,0) not null ,
  "nome" varchar(50) null ,
  "descr" varchar(100) null ,
  "fcall" varchar(50) null ,
  primary key ("id")
); 


--
-- table structure for table `sm_rule`
--

create table "sm_rule" (  "id" numeric(22,0) not null ,
  "code" varchar(50) null ,
  "descr" varchar(200) null ,
  "rtype" numeric(22,0) null ,
  "errcode" numeric(22,0) null ,
  "active" numeric(22,0) null ,
  "formula" text null ,
  "ruleset" numeric(22,0) null ,
  "variabile" varchar(50) null ,
  "formula_ori" varchar(100) null ,
  primary key ("id"),foreign key ("ruleset") references "sm_ruleset" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_parametri`
--

create table "sm_parametri" (  "id" numeric(22,0) not null ,
  "nome" varchar(200) null ,
  "descr" text null ,
  "valore" varchar(200) null ,
  "tipo" numeric(22,0) null ,
  primary key ("id")
); 


--
-- table structure for table `sm_population`
--

create table "sm_population" (  "id" numeric(22,0) not null ,
  "descrizione" varchar(100) null ,
  "edizione" numeric(22,0) not null ,
  primary key ("id"),foreign key ("edizione") references "sm_edizione" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_unit_edition_fase`
--

create table "sm_unit_edition_fase" (  "id" numeric(22,0) not null ,
  "nome" varchar(50) null ,
  "descr" varchar(100) null ,
  "edizione" numeric(22,0) null ,
  "fase" numeric(22,0) null ,
  "unit" numeric(22,0) null ,
  primary key ("id"),foreign key ("unit") references "sm_unit" ( "id" ) on update no action on delete no action,foreign key ("fase") references "sm_fase" ( "id" ) on update no action on delete no action,foreign key ("edizione") references "sm_edizione" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_workflow`
--

create table "sm_workflow" (  "step" numeric(22,0) null ,
  "active" numeric(22,0) null ,
  "regola" numeric(22,0) null ,
  "action" numeric(22,0) null ,
  "eccezione" numeric(22,0) null ,
  "livello" numeric(22,0) null ,foreign key ("step") references "sm_business_step" ( "id" ) on update no action on delete no action,foreign key ("regola") references "sm_rule" ( "id" ) on update no action on delete no action,foreign key ("action") references "sm_business_step" ( "id" ) on update no action on delete no action,foreign key ("eccezione") references "sm_business_step" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_link_process_step`
--

create table "sm_link_process_step" (  "bstep" numeric(22,0) not null ,
  "bprocess" numeric(22,0) not null ,
  primary key ("bstep","bprocess"),foreign key ("bprocess") references "sm_business_process" ( "id" ) on update no action on delete no action,foreign key ("bstep") references "sm_business_step" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_tipo_oggetto`
--

create table "sm_tipo_oggetto" (  "id" numeric(22,0) not null ,
  "nome" varchar(128) null ,
  "descr" text null ,
  primary key ("id")
); 


--
-- table structure for table `sm_workset`
--

create table "sm_workset" (  "id" numeric(22,0) not null ,
  "descrizione" text null ,
  "id_oggetto" numeric(22,0) null ,
  "tipo_oggetto" numeric(22,0) null ,
  "tipo_io" numeric(22,0) null ,
  "istanza" numeric(22,0) null ,
  "ordine" numeric(22,0) not null ,
  "nome_oggetto" varchar(16) null ,
  primary key ("id"),foreign key ("tipo_oggetto") references "sm_tipo_oggetto" ( "id" ) on update no action on delete no action,foreign key ("istanza") references "sm_step_instance" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_dataset`
--

create table "sm_dataset" (  "id" numeric(22,0) not null ,
  "tabella" varchar(100) not null ,
  "tipo_tabella" numeric(22,0) null ,
  "unit" numeric(22,0) null ,
  "table_name" varchar(100) null ,
  "table_key" varchar(100) null ,
  "table_master" varchar(100) null ,
  "key_master" varchar(100) null ,
  unique ("id"),foreign key ("tipo_tabella") references "sm_tipo_tabella" ( "id" ) on update no action on delete no action,foreign key ("unit") references "sm_unit" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_variable`
--

create table "sm_variable" (  "id" numeric(22,0) not null ,
  "id_variable" varchar(150) not null ,
  "nome_variable_it" text not null ,
  "nome_variable_en" text null ,
  "definizione_it" text null ,
  "definizione_en" text null ,
  "raggruppamento" varchar(150) null ,
  "tipo_variabile" varchar(6) not null ,
  "tipo_variabile_id" numeric(22,0) not null ,
  primary key ("id"),foreign key ("tipo_variabile_id") references "sm_variabile_type" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `wd_edizione`
--

create table "wd_edizione" (  "id" numeric(22,0) not null ,
  "anno" smallint not null ,
  "mese" smallint not null ,
  "user_lavorazione" numeric(22,0) null ,
  "data_ultima_lavorazione" date null ,
  "tipo" varchar(100) null ,
  "indagine" varchar(100) null ,
  "user_ultima_lavorazione" varchar(100) null ,
  "data_inizio" date null ,
  "data_fine" date null ,
  "periodicita" smallint null ,
  "edizione" numeric(22,0) not null ,
  primary key ("id"),foreign key ("edizione") references "sm_edizione" ( "id" ) on update no action on delete no action
); 
create index "wd_edizione_ind_edi" on "wd_edizione" ("edizione");


--
-- table structure for table `sm_unit_join`
--

create table "sm_unit_join" (  "id" numeric(22,0) null ,
  "process" numeric(22,0) null ,
  "unit" varchar(128) null ,
  "table_master" varchar(128) null ,
  "key_master" varchar(128) null ,
  "table_name" varchar(128) null ,
  "key_name" varchar(128) null ,
  "dblink" varchar(200) null ,
  "where_stm" text null 
); 


--
-- table structure for table `sm_unit_select`
--

create table "sm_unit_select" (  "process" numeric(22,0) null ,
  "table_name" varchar(128) null ,
  "variabile" text null ,
  "col_name" varchar(128) null 
); 


--
-- table structure for table `sm_ediz_unit_var`
--

create table "sm_ediz_unit_var" (  "id" numeric(22,0) not null ,
  "variabile" numeric(22,0) null ,
  "ordine" smallint null ,
  "unit" numeric(22,0) null ,
  "alias_sql" varchar(100) null ,
  "attiva" smallint null default 1,
  primary key ("id"),foreign key ("variabile") references "sm_variable" ( "id" ) on update no action on delete no action,foreign key ("unit") references "sm_unit" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_instance_variable`
--

create table "sm_instance_variable" (  "id" numeric(20,0) not null ,
  "nome_campo" varchar(50) not null ,
  "descrizione" varchar(100) null ,
  "etichetta" varchar(50) null ,
  "tipo" varchar(30) null ,
  "inf" varchar(30) null ,
  "sup" varchar(30) null ,
  "miss" varchar(30) null ,
  "indagine" varchar(10) null ,
  "length" bigint null ,
  "tipo_variabile_sum" smallint null ,
  "id_variable" numeric(22,0) null ,
  primary key ("id"),foreign key ("id_variable") references "sm_variable" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_ediz_unit_var_modeldata`
--

create table "sm_ediz_unit_var_modeldata" (  "flag_lista" smallint null ,
  "flag_chiave" smallint null ,
  "flag_sort" smallint null ,
  "flag_fisso" smallint null ,
  "flag_chiave_ext" smallint null ,
  "flag_chiave_int" smallint null ,
  "flag_attivo" smallint null ,
  "flag_oggetto" smallint null ,
  "condizione" text null ,
  "sm_ediz_uv_id" numeric(22,0) not null ,
  primary key ("sm_ediz_uv_id"),foreign key ("sm_ediz_uv_id") references "sm_ediz_unit_var" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_link_function_process`
--

create table "sm_link_function_process" (  "bfunction" numeric(22,0) not null ,
  "bprocess" numeric(22,0) not null ,
  primary key ("bprocess","bfunction"),foreign key ("bprocess") references "sm_business_process" ( "id" ) on update no action on delete no action,foreign key ("bfunction") references "sm_business_function" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `sm_link_step_instance`
--

create table "sm_link_step_instance" (  "istanza" numeric(22,0) null ,
  "step" numeric(22,0) null ,foreign key ("step") references "sm_business_step" ( "id" ) on update no action on delete no action,foreign key ("istanza") references "sm_step_instance" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `tipo_variabile_sum`
--

create table "tipo_variabile_sum" (  "nome_tipo_variabile_ita" text null ,
  "nome_tipo_variabile_eng" text null ,
  "tipo_variabile" varchar(6) not null ,
  "titolo" text null ,
  "ordine" smallint null 
); 


--
-- table structure for table `sm_link_edizione_function`
--

create table "sm_link_edizione_function" (  "edizione" numeric(22,0) null ,
  "funzione" numeric(22,0) null ,
  "quando" date null ,
  "attiva" numeric(22,0) null ,
  "eseguita" numeric(22,0) null ,
  "errore" numeric(22,0) null ,
  "utente" numeric(22,0) null ,foreign key ("edizione") references "sm_business_function" ( "id" ) on update no action on delete no action,foreign key ("funzione") references "sm_edizione" ( "id" ) on update no action on delete no action
); 


--
-- table structure for table `wd_edizione_old`
--

create table "wd_edizione_old" (  "id" numeric(22,0) not null ,
  "edizione" numeric(22,0) not null ,
  "mese" numeric(22,0) null ,
  "anno" numeric(22,0) null ,
  "periodicita" numeric(22,0) not null ,
  primary key ("id")
); 

