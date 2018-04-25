-- begin DDCDI_IMPORT_LOG
alter table DDCDI_IMPORT_LOG add constraint FK_DDCDI_IMPORT_LOG_FILE foreign key (FILE_ID) references SYS_FILE(ID)^
alter table DDCDI_IMPORT_LOG add constraint FK_DDCDI_IMPORTLOG_CONFIG foreign key (CONFIGURATION_ID) references DDCDI_IMPORT_CONFIGURATION(ID)^
create index IDX_DDCDI_IMPORT_LOG_FILE on DDCDI_IMPORT_LOG (FILE_ID)^
create index IDX_DDCDI_IMPORTLOG_CONFIG on DDCDI_IMPORT_LOG (CONFIGURATION_ID)^
-- end DDCDI_IMPORT_LOG
-- begin DDCDI_IMPORT_LOG_RECORD
alter table DDCDI_IMPORT_LOG_RECORD add constraint FK_DDCDI_IMPLOGREC_IMPORT_LOG foreign key (IMPORT_LOG_ID) references DDCDI_IMPORT_LOG(ID)^
create index IDX_DDCDI_IMPLOGREC_IMPLOG on DDCDI_IMPORT_LOG_RECORD (IMPORT_LOG_ID)^
-- end DDCDI_IMPORT_LOG_RECORD
-- begin DDCDI_IMPORT_CONFIGURATION
alter table DDCDI_IMPORT_CONFIGURATION add constraint FK_DDCDI_IMPORTCONFIG_TEMPLA foreign key (TEMPLATE_ID) references SYS_FILE(ID)^
create index IDX_DDCDI_IMPORTCONFIG_TEMPLA on DDCDI_IMPORT_CONFIGURATION (TEMPLATE_ID)^
-- end DDCDI_IMPORT_CONFIGURATION
-- begin DDCDI_IMPORT_ATTRIBUTE_MAPPER
alter table DDCDI_IMPORT_ATTRIBUTE_MAPPER add constraint FK_DDCDI_IMPOATTRMAPP_CONF foreign key (CONFIGURATION_ID) references DDCDI_IMPORT_CONFIGURATION(ID)^
create index IDX_DDCDI_IMPOATTRMAPP_CONF on DDCDI_IMPORT_ATTRIBUTE_MAPPER (CONFIGURATION_ID)^
-- end DDCDI_IMPORT_ATTRIBUTE_MAPPER
-- begin DDCDI_UNIQUE_CONFIGURATION_ATTRIBUTE
alter table DDCDI_UNIQUE_CONFIGURATION_ATTRIBUTE add constraint FK_DDCDI_UNICONATT_UNICON foreign key (UNIQUE_CONFIGURATION_ID) references DDCDI_UNIQUE_CONFIGURATION(ID)^
create index IDX_DDCDI_UNICONATT_UNICON on DDCDI_UNIQUE_CONFIGURATION_ATTRIBUTE (UNIQUE_CONFIGURATION_ID)^
-- end DDCDI_UNIQUE_CONFIGURATION_ATTRIBUTE
-- begin DDCDI_UNIQUE_CONFIGURATION
alter table DDCDI_UNIQUE_CONFIGURATION add constraint FK_DDCDI_UNIQCONF_IMPOCONF foreign key (IMPORT_CONFIGURATION_ID) references DDCDI_IMPORT_CONFIGURATION(ID)^
create index IDX_DDCDI_UNIQCONF_IMPOCONF on DDCDI_UNIQUE_CONFIGURATION (IMPORT_CONFIGURATION_ID)^
-- end DDCDI_UNIQUE_CONFIGURATION