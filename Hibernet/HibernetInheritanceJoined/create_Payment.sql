
CREATE SCHEMA payment;

CREATE TABLE payment.jc_payment
(
  payment_id SERIAL PRIMARY KEY ,
  amount DOUBLE PRECISION NOT NULL

  );

ALTER TABLE payment.jc_payment OWNER TO postgres;


CREATE TABLE payment.jc_cash_payment
(
  payment_id INTEGER REFERENCES  payment.jc_payment(payment_id) ,
  cash_desk character varying(255) NOT NULL,
  PRIMARY KEY (payment_id)
 );

ALTER TABLE  payment.jc_cash_payment OWNER TO postgres;

CREATE TABLE payment.jc_credit_payment
(
  payment_id INTEGER REFERENCES  payment.jc_payment(payment_id),
  credit_card character varying(16) NOT NULL,
  PRIMARY KEY (payment_id)
 );

ALTER TABLE  payment.jc_credit_payment OWNER TO postgres;
--
insert into payment.jc_payment  (amount) values (100);
insert into payment.jc_credit_payment  (payment_id,credit_card ) values (5,'434485120071413');
insert into payment.jc_payment  (amount) values (200);
insert into payment.jc_credit_payment  (credit_card ) values ('554483120071420');
insert into payment.jc_payment  (amount) values (300);
insert into payment.jc_cash_payment  (cash_desk ) values ('Kassa1');


SELECT jc_credit_payment.payment_id,credit_card,amount FROM payment.jc_credit_payment,payment.jc_payment  WHERE jc_payment.payment_id=jc_credit_payment.payment_id

-- CREATE TABLE books.jc_books_author
-- (
--   book_id INT REFERENCES books.jc_books(book_id)
--                               ON DELETE CASCADE
--                               ON UPDATE CASCADE ,
--   author_id INT  REFERENCES books.jc_author(author_id)
--                               ON DELETE CASCADE
--                               ON UPDATE CASCADE,
--   PRIMARY KEY (book_id, author_id )
--  );


-- insert into books.jc_author  (author_name) values ('Tolstoy');
-- insert into books.jc_author  (author_name) values ('Dostoevsiy');
-- insert into books.jc_author  (author_name) values ('Smirmov');
-- insert into books.jc_author  (author_name) values ('Esenin');
-- insert into books.jc_author  (author_name) values ('Pushkin');
--
--
-- insert into books.jc_books  (book_name) values ('War and Peace');
-- insert into books.jc_books  (book_name) values ('Fathers and Sons');
-- insert into books.jc_books  (book_name) values ('Market');
-- insert into books.jc_books  (book_name) values ('A Crime');
-- insert into books.jc_books  (book_name) values ('Stichi');
-- insert into books.jc_books  (book_name) values ('Groza');
--
--
-- insert into books.jc_books_author  (book_id, author_id ) values (1,1);
-- insert into books.jc_books_author  (book_id, author_id) values (1,2);
-- insert into books.jc_books_author  (book_id, author_id) values (1,3);
-- insert into books.jc_books_author  (book_id, author_id) values (2,3);
-- insert into books.jc_books_author  (book_id, author_id) values (3,3);

