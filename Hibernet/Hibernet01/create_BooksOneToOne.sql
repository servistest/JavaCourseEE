CREATE SCHEMA books;

CREATE TABLE books.jc_author
(
  author_id bigserial PRIMARY KEY ,
  author_name character varying(255)
  )

WITH (
OIDS=FALSE
);
ALTER TABLE books.jc_author
OWNER TO postgres;


CREATE TABLE books.jc_books
(
  book_id SERIAL PRIMARY KEY  ,
  book_name character varying(255) NOT NULL ,
  author_id INT  UNIQUE  REFERENCES books.jc_author (author_id)
 );


ALTER TABLE books.jc_books
OWNER TO postgres;


insert into books.jc_author  (author_name) values ('Tolstoy');
insert into books.jc_author  (author_name) values ('Dostoevsiy');
insert into books.jc_author  (author_name) values ('Smirmov');
insert into books.jc_author  (author_name) values ('Esenin');
insert into books.jc_author  (author_name) values ('Pushkin');
insert into books.jc_author  (author_name) values ('Rusov');




insert into books.jc_books  (book_name,author_id) values ('War and Peace',1);
insert into books.jc_books  (book_name,author_id) values ('Fathers and Sons',2);
insert into books.jc_books  (book_name,author_id) values ('Market',3);
insert into books.jc_books  (book_name,author_id) values ('A Crime',4);
insert into books.jc_books  (book_name,author_id) values ('Stichi',5);
insert into books.jc_books  (book_name,author_id) values ('Groza',6);

