
CREATE SCHEMA books;

CREATE TABLE books.jc_author
(
  author_id bigserial PRIMARY KEY ,
  author_name character varying(255) NOT NULL
  )

WITH (
OIDS=FALSE
);
ALTER TABLE books.jc_author
OWNER TO postgres;


CREATE TABLE books.jc_books
(
  book_id SERIAL PRIMARY KEY  ,
  book_name character varying(255) NOT NULL
 );

ALTER TABLE books.jc_books
OWNER TO postgres;

CREATE TABLE books.jc_books_author
(
  book_id INT REFERENCES books.jc_books(book_id)
                              ON DELETE CASCADE
                              ON UPDATE CASCADE ,
  author_id INT  REFERENCES books.jc_author(author_id)
                              ON DELETE CASCADE
                              ON UPDATE CASCADE,
  PRIMARY KEY (book_id, author_id )
 );


insert into books.jc_author  (author_name) values ('Tolstoy');
insert into books.jc_author  (author_name) values ('Dostoevsiy');
insert into books.jc_author  (author_name) values ('Smirmov');
insert into books.jc_author  (author_name) values ('Esenin');
insert into books.jc_author  (author_name) values ('Pushkin');


insert into books.jc_books  (book_name) values ('War and Peace');
insert into books.jc_books  (book_name) values ('Fathers and Sons');
insert into books.jc_books  (book_name) values ('Market');
insert into books.jc_books  (book_name) values ('A Crime');
insert into books.jc_books  (book_name) values ('Stichi');
insert into books.jc_books  (book_name) values ('Groza');


insert into books.jc_books_author  (book_id, author_id ) values (1,1);
insert into books.jc_books_author  (book_id, author_id) values (1,2);
insert into books.jc_books_author  (book_id, author_id) values (1,3);
insert into books.jc_books_author  (book_id, author_id) values (2,3);
insert into books.jc_books_author  (book_id, author_id) values (3,3);

