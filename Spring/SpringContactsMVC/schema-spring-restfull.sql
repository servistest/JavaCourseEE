  DROP  SCHEMA IF EXISTS mvc CASCADE ;
  CREATE SCHEMA IF NOT EXISTS mvc ;


  CREATE TABLE mvc.CONTACT(
    ID BIGSERIAL ,
    FIRST_NAME VARCHAR(60) NOT NULL,
    LAST_NAME VARCHAR(40) NOT NULL,
    BIRTH_DATE DATE,
    DESCRIPTION VARCHAR(2000),
    PHOTO BYTEA,
    UNIQUE (FIRST_NAME,LAST_NAME),
    PRIMARY KEY (ID)
  );



insert into mvc.contact (first_name, last_name, birth_date) values ('Chris', 'Schaefer', '1981-05-03');
insert into mvc.contact (first_name, last_name, birth_date) values ('Scott', 'Tiger', '1990-11-02');
insert into mvc.contact (first_name, last_name, birth_date) values ('John', 'Smith', '1964-02-28');
  insert into mvc.contact (first_name, last_name, birth_date) values ('Paul', 'Wolf', '1981-02-01');
  insert into mvc.contact (first_name, last_name, birth_date) values ('Smith', 'Woods', '1993-01-02');
  insert into mvc.contact (first_name, last_name, birth_date) values ('Henry', 'Jackson', '1954-06-28');
  insert into mvc.contact (first_name, last_name, birth_date) values ('Alex', 'Smirnov', '1979-03-06');
  insert into mvc.contact (first_name, last_name, birth_date) values ('Raul', 'Chan', '1960-10-22');
  insert into mvc.contact (first_name, last_name, birth_date) values ('Andrew', 'Bouly', '1934-05-18');
  insert into mvc.contact (first_name, last_name, birth_date) values ('Sasha', 'Turner', '1951-08-03');
  insert into mvc.contact (first_name, last_name, birth_date) values ('Amanda', 'Notes', '1954-10-01');
  insert into mvc.contact (first_name, last_name, birth_date) values ('Sam', 'Dikson', '1965-02-18');
  insert into mvc.contact (first_name, last_name, birth_date) values ('Max', 'Davis', '1986-05-03');
  insert into mvc.contact (first_name, last_name, birth_date) values ('Susan', 'Backhem', '1992-11-06');
  insert into mvc.contact (first_name, last_name, birth_date) values ('Tiner', 'Simon', '1961-05-26');
