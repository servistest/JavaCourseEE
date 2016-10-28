  DROP  SCHEMA IF EXISTS resttest CASCADE ;
  CREATE SCHEMA IF NOT EXISTS resttest ;


  CREATE TABLE resttest.CONTACT(
    ID BIGSERIAL ,
    FIRST_NAME VARCHAR(60) NOT NULL,
    LAST_NAME VARCHAR(40) NOT NULL,
    BIRTH_DATE DATE,
    UNIQUE (FIRST_NAME,LAST_NAME),
    PRIMARY KEY (ID)
  );


insert into resttest.contact (first_name, last_name, birth_date) values ('Chris', 'Schaefer', '1981-05-03');
insert into resttest.contact (first_name, last_name, birth_date) values ('Scott', 'Tiger', '1990-11-02');
insert into resttest.contact (first_name, last_name, birth_date) values ('John', 'Smith', '1964-02-28');
