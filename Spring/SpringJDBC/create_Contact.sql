CREATE SCHEMA contact;

CREATE TABLE contact.CONTACT(
  ID BIGSERIAL ,
  FIRST_NAME VARCHAR(60) NOT NULL,
  LAST_NAME VARCHAR(40) NOT NULL,
  BIRTH_DATE DATE,
  UNIQUE (FIRST_NAME,LAST_NAME),
  PRIMARY KEY (ID)
);

CREATE TABLE contact.CONTACT_TEL_DETAIL(
  ID BIGSERIAL,
  CONTACT_ID INTEGER NOT NULL REFERENCES contact.CONTACT(ID),
  TEL_TYPE VARCHAR(20),
  TEL_NUMBER VARCHAR(20),
  UNIQUE (CONTACT_ID,TEL_TYPE),
  PRIMARY KEY (ID)
);

insert into contact.contact (first_name, last_name, birth_date) values
  ('Chris', 'Schaefer', '1981-05-03');
insert into contact.contact (first_name, last_name, birth_date) values
  ('Scott', 'Tiger', '1990-11-02');
insert into contact.contact (first_name, last_name, birth_date) values
  ('John', 'Smith', '1964-02-28');
insert into contact.contact_tel_detail (contact_id, tel_type, tel_number) values
  (1, 'Mobile', '1234567890');
insert into contact.contact_tel_detail (contact_id, tel_type, tel_number) values
(1, 'Home', '1234567890');
insert into contact.contact_tel_detail (contact_id, tel_type, tel_number) values
(2, 'Home', '1234567890');