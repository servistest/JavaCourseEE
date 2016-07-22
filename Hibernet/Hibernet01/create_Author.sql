DROP SCHEMA books;

CREATE SCHEMA books;
--set schema books;
--connect company;

create table books.jc_author
(
  author_id int  not null PRIMARY KEY,
  name varchar(255) not null,
  author int

) ;


insert into books.jc_books  (order_id,name) values (1,'War and Peace');
insert into books.jc_books  (order_id,name) values (2,'Fathers and Sons');
insert into books.jc_books  (order_id,name) values (3,'Market');
insert into books.jc_books  (order_id,name) values (4,'A Crime');