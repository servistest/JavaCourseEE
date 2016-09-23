CREATE SCHEMA hobby;

CREATE TABLE hobby.CONTACT(
  ID BIGSERIAL ,
  FIRST_NAME VARCHAR(60) NOT NULL,
  LAST_NAME VARCHAR(40) NOT NULL,
  BIRTH_DATE DATE,
  VERSION INT NOT NULL DEFAULT 0,
  UNIQUE (FIRST_NAME,LAST_NAME),
  PRIMARY KEY (ID)
);
CREATE TABLE hobby.CONTACT_AUDIT(
  ID BIGSERIAL NOT NULL ,
  FIRST_NAME VARCHAR(60) NOT NULL,
  LAST_NAME VARCHAR(40) NOT NULL,
  BIRTH_DATE DATE,
  VERSION INT NOT NULL DEFAULT 0,
  CREATED_BY VARCHAR(20),
  CREATED_DATE TIMESTAMP,
  LAST_MODIFIED_BY VARCHAR(20),
  LAST_MODIFIED_DATE TIMESTAMP,
  UNIQUE (FIRST_NAME,LAST_NAME),
  PRIMARY KEY (ID)
);

CREATE TABLE hobby.USER_AUDIT(
  ID BIGSERIAL NOT NULL ,
  NAME VARCHAR(60) NOT NULL,
  VERSION INT NOT NULL DEFAULT 0,
  CREATEDBY_ID VARCHAR(20),
  CREATEDDATE TIMESTAMP,
  LASTMODIFIEDBY VARCHAR(20),
  LASTMODIFIEDBY_ID VARCHAR(20),
  LASTMODIFIEDDATE TIMESTAMP,
  PRIMARY KEY (ID)
);

CREATE TABLE hobby.CONTACT_TEL_DETAIL(
  ID BIGSERIAL,
  CONTACT_ID INTEGER NOT NULL REFERENCES hobby.CONTACT(ID),
  TEL_TYPE VARCHAR(20),
  TEL_NUMBER VARCHAR(20),
  VERSION INT NOT NULL DEFAULT 0,
  UNIQUE (CONTACT_ID,TEL_TYPE),
  PRIMARY KEY (ID)
);

CREATE TABLE hobby.Hobby(
  HOBBY_ID VARCHAR(20),
  PRIMARY KEY (HOBBY_ID)
);

CREATE TABLE hobby.CONTACT_HOBBY_DETAIL(
  CONTACT_ID INTEGER NOT NULL REFERENCES hobby.CONTACT(ID) ON DELETE CASCADE ,
  HOBBY_ID VARCHAR(20) NOT NULL REFERENCES hobby.Hobby(HOBBY_ID),
  PRIMARY KEY (CONTACT_ID,HOBBY_ID)
);

insert into hobby.contact (first_name, last_name, birth_date) values ('Chris', 'Schaefer', '1981-05-03');
insert into hobby.contact (first_name, last_name, birth_date) values ('Scott', 'Tiger', '1990-11-02');
insert into hobby.contact (first_name, last_name, birth_date) values ('John', 'Smith', '1964-02-28');
insert into hobby.contact_tel_detail (contact_id, tel_type, tel_number) values ( 1, 'MoЬile', '1234567890');
insert into hobby.contact_tel_detail (contact_id, tel_type, tel_number) values (1, 'Home', '1234567890');
insert into hobby.contact_tel_detail (contact_id, tel_type, tel_number) values (2, 'Home', '1234567890');
insert into hobby.hobby (hobby_id) values ( 'Swimming' ) ;
insert into hobby.hobby (hobby_id) values ( 'Jogging') ;
insert into hobby.hobby (hobby_id) values ('Programming');
insert into hobby.hobby (hobby_id) values ( 'Movies');
insert into hobby.hobby (hobby_id) values ( 'Reading' ) ;
insert into hobby.CONTACT_HOBBY_DETAIL(contact_id, hobby_id) values (1, 'Swimming');
insert into hobby.contact_hobby_detail(contact_id, hobby_id) values (1, 'Movies');
insert into hobby.contact_hobby_detail(contact_id, hobby_id) values (2, 'Swimming');