
CREATE FUNCTION contact.getFirstNameById(int_id INT) RETURNS VARCHAR(60)AS '
  SELECT contact.first_name FROM contact.contact WHERE contact.id=int_id
' LANGUAGE SQL
