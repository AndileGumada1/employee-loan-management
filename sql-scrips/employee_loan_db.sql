
CREATE TABLE employee (
  id int NOT NULL,
  branch_name varchar(255) DEFAULT NULL,
  confirm_password varchar(255) DEFAULT NULL,
  department varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  employee_number varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  phone bigint NOT NULL,
  PRIMARY KEY (id)
) 

CREATE TABLE loan (
  loan_id int NOT NULL,
  loan_amount double NOT NULL,
  manager_name varchar(255) DEFAULT NULL,
  reason varchar(255) DEFAULT NULL,
  id int DEFAULT NULL,
  PRIMARY KEY (loan_id),
  KEY FK87npfh0hb1qf7j27f26b0r694 (id),
  CONSTRAINT FK87npfh0hb1qf7j27f26b0r694 FOREIGN KEY (id) REFERENCES employee (id)
) 


CREATE TABLE transaction (
  transactions_id int NOT NULL,
  status varchar(255) DEFAULT NULL,
  transaction_time datetime(6) DEFAULT NULL,
  loan_id int DEFAULT NULL,
  PRIMARY KEY (transactions_id),
  KEY FKkp2yyh868utly7r7ntwarpn6a (loan_id),
  CONSTRAINT FKkp2yyh868utly7r7ntwarpn6a FOREIGN KEY (loan_id) REFERENCES loan (loan_id)
) 