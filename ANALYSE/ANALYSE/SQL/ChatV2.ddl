-- *********************************************
-- * SQL MySQL generation                      
-- *--------------------------------------------
-- * DB-MAIN version: 9.1.6              
-- * Generator date: Feb 25 2013              
-- * Generation date: Sun Jun 01 21:04:56 2014 
-- * LUN file: C:\Users\Lwaxana\Desktop\ANALYSE\DB\ChatV1.lun 
-- * Schema: mcd5/2/1-1 
-- ********************************************* 


-- Database Section
-- ________________ 

create database chat3;
use chat3;


-- Tables Section
-- _____________ 

create table activitesalon (
     idactivite int not null AUTO_INCREMENT,
     heure date not null,
     activite varchar(250) not null,
     iduser int not null,
     idtype int not null,
     idsalon int not null,
     constraint ID_activitesalon_ID primary key (idactivite));

create table activiteserveur (
     idactivite int not null AUTO_INCREMENT,
     heure date not null,
     description varchar(250) not null,
     idsalon int null,
     idserveur int not null,
     iduser int null,
     idtype int not null,
     constraint ID_activiteserveur_ID primary key (idactivite));

create table administrateur (
     idadministrateur int not null AUTO_INCREMENT,
     iduser int not null,
     constraint ID_administrateur_ID primary key (idadministrateur),
     constraint FKuse_adm_ID unique (iduser));

create table adresse (
     idadresse int not null AUTO_INCREMENT,
     rue varchar(100) not null,
     numero varchar(10) not null,
     ville varchar(50) not null,
     zip varchar(10) not null,
     idpays int not null,
     constraint ID_adresse_ID primary key (idadresse));

create table gere (
     idadministrateur int not null ,
     idserveur int not null,
     constraint ID_gere_ID primary key (idserveur, idadministrateur));

create table logsalon (
     idlog int not null AUTO_INCREMENT,
     date date not null,
     log varchar(250) not null,
     idsalon int not null,
     constraint ID_logsalon_ID primary key (idlog));

create table logserveur (
     idlog int not null AUTO_INCREMENT,
     date date not null,
     log varchar(250) not null,
     idserveur int not null,
     constraint ID_logserveur_ID primary key (idlog));

create table moderateur (
     idmoderateur int not null AUTO_INCREMENT,
     iduser int not null,
     constraint ID_moderateur_ID primary key (idmoderateur),
     constraint FKuse_mod_ID unique (iduser));

create table modere (
     idmoderateur int not null,
     idsalon int not null,
     constraint ID_modere_ID primary key (idsalon, idmoderateur));

create table pays (
     idpays int not null AUTO_INCREMENT,
     nompays varchar(100) not null,
     constraint ID_pays_ID primary key (idpays));

create table salon (
     idsalon int not null AUTO_INCREMENT,
     nom varchar(50) not null,
     password varchar(20) null,
     iduser int null,
     idtype int not null,
     idserveur int not null,
     constraint ID_salon_ID primary key (idsalon));

create table serveur (
     idserveur int not null AUTO_INCREMENT,
     nom varchar(50) not null,
     IP varchar(15) not null,
     port int not null,
     password varchar(64) null,
     online char not null,
     constraint ID_serveur_ID primary key (idserveur));

create table typeactiviteserveur (
     idtype int not null AUTO_INCREMENT,
     type varchar(50) not null,
     constraint ID_typeactiviteserveur_ID primary key (idtype));

create table typepactivitesalon (
     idtype int not null AUTO_INCREMENT,
     type varchar(100) not null,
     constraint ID_typepactivitesalon_ID primary key (idtype));

create table typesalon (
     idtype int not null AUTO_INCREMENT,
     type varchar(50) not null,
     constraint ID_typesalon_ID primary key (idtype));

create table user (
     iduser int not null AUTO_INCREMENT,
     prenom varchar(25) not null,
     nom varchar(50) not null,
     email varchar(50) not null UNIQUE,
     password varchar(64) not null,
     nick varchar(20) not null,
     ban tinyint not null,
     online tinyint not null,
     idadresse int not null,
     constraint ID_user_ID primary key (iduser));


-- Constraints Section
-- ___________________ 

alter table activitesalon add constraint FKcree_FK
     foreign key (iduser)
     references user (iduser);

alter table activitesalon add constraint FKa_un_type_FK
     foreign key (idtype)
     references typepactivitesalon (idtype);

alter table activitesalon add constraint FKa_FK
     foreign key (idsalon)
     references salon (idsalon);

alter table activiteserveur add constraint FKenregistre2_FK
     foreign key (idsalon)
     references salon (idsalon);

alter table activiteserveur add constraint FKenregistre_3_FK
     foreign key (idserveur)
     references serveur (idserveur);

alter table activiteserveur add constraint FKenregistre_FK
     foreign key (iduser)
     references user (iduser);

alter table activiteserveur add constraint FKdu_type_FK
     foreign key (idtype)
     references typeactiviteserveur (idtype);

-- Not implemented
-- alter table administrateur add constraint ID_administrateur_CHK
--     check(exists(select * from gere
--                  where gere.idadministrateur = idadministrateur)); 

alter table administrateur add constraint FKuse_adm_FK
     foreign key (iduser)
     references user (iduser);

-- Not implemented
-- alter table adresse add constraint ID_adresse_CHK
--     check(exists(select * from user
--                  where user.idadresse = idadresse)); 

alter table adresse add constraint FKse_trouve_FK
     foreign key (idpays)
     references pays (idpays);

alter table gere add constraint FKger_ser
     foreign key (idserveur)
     references serveur (idserveur);

alter table gere add constraint FKger_adm_FK
     foreign key (idadministrateur)
     references administrateur (idadministrateur);

alter table logsalon add constraint FKgenere_FK
     foreign key (idsalon)
     references salon (idsalon);

alter table logserveur add constraint FKgenere2_FK
     foreign key (idserveur)
     references serveur (idserveur);

-- Not implemented
-- alter table moderateur add constraint ID_moderateur_CHK
--     check(exists(select * from modere
--                  where modere.idmoderateur = idmoderateur)); 

alter table moderateur add constraint FKuse_mod_FK
     foreign key (iduser)
     references user (iduser);

alter table modere add constraint FKmod_sal
     foreign key (idsalon)
     references salon (idsalon);

alter table modere add constraint FKmod_mod_FK
     foreign key (idmoderateur)
     references moderateur (idmoderateur);

-- Not implemented
-- alter table pays add constraint ID_pays_CHK
--     check(exists(select * from adresse
--                  where adresse.idpays = idpays)); 

-- Not implemented
-- alter table salon add constraint ID_salon_CHK
--     check(exists(select * from logsalon
--                  where logsalon.idsalon = idsalon)); 

-- Not implemented
-- alter table salon add constraint ID_salon_CHK
--     check(exists(select * from modere
--                  where modere.idsalon = idsalon)); 

alter table salon add constraint FKpeut_creer_FK
     foreign key (iduser)
     references user (iduser);

alter table salon add constraint FKest_du_type_FK
     foreign key (idtype)
     references typesalon (idtype);

alter table salon add constraint FKcontient_FK
     foreign key (idserveur)
     references serveur (idserveur);

-- Not implemented
-- alter table serveur add constraint ID_serveur_CHK
--     check(exists(select * from salon
--                  where salon.idserveur = idserveur)); 

-- Not implemented
-- alter table serveur add constraint ID_serveur_CHK
--     check(exists(select * from gere
--                  where gere.idserveur = idserveur)); 

-- Not implemented
-- alter table typeactiviteserveur add constraint ID_typeactiviteserveur_CHK
--     check(exists(select * from activiteserveur
--                  where activiteserveur.idtype = idtype)); 

-- Not implemented
-- alter table typepactivitesalon add constraint ID_typepactivitesalon_CHK
--     check(exists(select * from activitesalon
--                  where activitesalon.idtype = idtype)); 

-- Not implemented
-- alter table typesalon add constraint ID_typesalon_CHK
--     check(exists(select * from salon
--                  where salon.idtype = idtype)); 

alter table user add constraint FKreside_FK
     foreign key (idadresse)
     references adresse (idadresse);


-- Index Section
-- _____________ 

create unique index ID_activitesalon_IND
     on activitesalon (idactivite);

create index FKcree_IND
     on activitesalon (iduser);

create index FKa_un_type_IND
     on activitesalon (idtype);

create index FKa_IND
     on activitesalon (idsalon);

create unique index ID_activiteserveur_IND
     on activiteserveur (idactivite);

create index FKenregistre2_IND
     on activiteserveur (idsalon);

create index FKenregistre_3_IND
     on activiteserveur (idserveur);

create index FKenregistre_IND
     on activiteserveur (iduser);

create index FKdu_type_IND
     on activiteserveur (idtype);

create unique index ID_administrateur_IND
     on administrateur (idadministrateur);

create unique index FKuse_adm_IND
     on administrateur (iduser);

create unique index ID_adresse_IND
     on adresse (idadresse);

create index FKse_trouve_IND
     on adresse (idpays);

create unique index ID_gere_IND
     on gere (idserveur, idadministrateur);

create index FKger_adm_IND
     on gere (idadministrateur);

create unique index ID_logsalon_IND
     on logsalon (idlog);

create index FKgenere_IND
     on logsalon (idsalon);

create unique index ID_logserveur_IND
     on logserveur (idlog);

create index FKgenere2_IND
     on logserveur (idserveur);

create unique index ID_moderateur_IND
     on moderateur (idmoderateur);

create unique index FKuse_mod_IND
     on moderateur (iduser);

create unique index ID_modere_IND
     on modere (idsalon, idmoderateur);

create index FKmod_mod_IND
     on modere (idmoderateur);

create unique index ID_pays_IND
     on pays (idpays);

create unique index ID_salon_IND
     on salon (idsalon);

create index FKpeut_creer_IND
     on salon (iduser);

create index FKest_du_type_IND
     on salon (idtype);

create index FKcontient_IND
     on salon (idserveur);

create unique index ID_serveur_IND
     on serveur (idserveur);

create unique index ID_typeactiviteserveur_IND
     on typeactiviteserveur (idtype);

create unique index ID_typepactivitesalon_IND
     on typepactivitesalon (idtype);

create unique index ID_typesalon_IND
     on typesalon (idtype);

create unique index ID_user_IND
     on user (iduser);

create index FKreside_IND
     on user (idadresse);

create unique index email_user
     on user (email);




