CREATE SCHEMA books;

CREATE TABLE books.jc_author
(
  author_id bigserial NOT NULL ,
  author_name character varying(255),
  CONSTRAINT jc_author_pkey PRIMARY KEY (author_id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE books.jc_author
OWNER TO postgres;


CREATE TABLE books.jc_books
(
  book_id bigserial NOT NULL  ,
  book_name character varying(255),
  --author_id INT REFERENCES books.jc_author
  author_id INT ,
  CONSTRAINT jc_books_pkey PRIMARY KEY (book_id),
   CONSTRAINT jc_books_fkey_books FOREIGN KEY (author_id)
  REFERENCES books.jc_author (author_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION

  );


ALTER TABLE books.jc_books
OWNER TO postgres;





insert into books.jc_author  (author_name) values ('Tolstoy');
insert into books.jc_author  (author_name) values ('Dostoevsiy');
insert into books.jc_author  (author_name) values ('Smirmov');
insert into books.jc_author  (author_name) values ('Esenin');



insert into books.jc_books  (book_name,author_id) values ('War and Peace',2);
insert into books.jc_books  (book_name,author_id) values ('Fathers and Sons',2);
insert into books.jc_books  (book_name,author_id) values ('Market',1);
insert into books.jc_books  (book_name,author_id) values ('A Crime',3);
insert into books.jc_books  (book_name,author_id) values ('Stichi',1);
insert into books.jc_books  (book_name,author_id) values ('Groza',4);

