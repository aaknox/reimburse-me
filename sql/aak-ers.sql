/*******************************************************************
 * 
 * AAK Employee Reimbursment System (ERS) Database - Version 1.0
   Script: aak-ers.sql
   Description: Creates and populates the AAK ERS database.
   DB Server: PostgreSQL
   Author: Azhya Knox

   Origin Date: 11/13/2020
   Summary:
    - Inital setup of database for Revature ERS Project
 * 
 ********************************************************************/

/************************************************************
 * 			DROP TABLES/PROCEDURES/FUNCTIONS
 ************************************************************/
--in correct order of deletion:
DROP TABLE IF EXISTS ers_reimbursements;
DROP TABLE IF EXISTS ers_reimbursements_status;
DROP TABLE IF EXISTS ers_reimbursements_types;
DROP TABLE IF EXISTS ers_users;
DROP TABLE IF EXISTS ers_user_roles;

--SEQUENCES
DROP SEQUENCE IF EXISTS reimb_sequence;
/************************************************************
 * 			CREATE TABLES / ADD CONSTRAINTS
 ************************************************************/
CREATE TABLE ers_users(
	ers_user_id serial PRIMARY KEY,
	ers_user_role_id int NOT NULL,
	ers_username varchar(50) UNIQUE NOT NULL,
	ers_user_password varchar(50) NOT NULL,
	ers_user_first_name varchar(50) NOT NULL,
	ers_user_last_name varchar(50) NOT NULL,
	ers_user_email varchar(100) UNIQUE NOT NULL,
	ers_user_hire_date date NOT NULL
);

CREATE TABLE ers_user_roles(
	ers_role_id serial PRIMARY KEY,
	ers_role_name varchar(50) NOT NULL 
);

-- ers_user_role_id is a foreign key within ers_users table
ALTER TABLE ers_users
ADD CONSTRAINT fk_constraint_usersToUserRoles
FOREIGN KEY (ers_user_role_id)
REFERENCES ers_user_roles (ers_role_id);

CREATE TABLE ers_reimbursements(
	reimb_id int PRIMARY KEY,
	reimb_amount decimal(10, 2),
	reimb_submitted timestamp,
	reimb_resolved timestamp,
	reimb_description varchar(250),
	reimb_receipt bytea,
	reimb_author_id int,
	reimb_resolver_id int,
	reimb_status_id int,
	reimb_type_id int
);

--create sequence for reimbursement id
CREATE SEQUENCE reimb_sequence
INCREMENT 100
START 100
OWNED BY ers_reimbursements.reimb_id;

CREATE TABLE ers_reimbursements_status(
	status_id serial PRIMARY KEY,
	status_name varchar(10)
);

CREATE TABLE ers_reimbursements_types(
	type_id serial PRIMARY KEY,
	type_name varchar(10)
);

-- status_id is a foreign key within ers_reimbursements table
ALTER TABLE ers_reimbursements
ADD CONSTRAINT fk_constraint_ersReimbToReimbStatus
FOREIGN KEY (reimb_status_id)
REFERENCES ers_reimbursements_status (status_id);

-- type_id is a foreign key within ers_reimbursements table
ALTER TABLE ers_reimbursements
ADD CONSTRAINT fk_constraint_ersReimbToReimbType
FOREIGN KEY (reimb_type_id)
REFERENCES ers_reimbursements_types (type_id);

-- author id is referring user table's user id 
ALTER TABLE ers_reimbursements
ADD CONSTRAINT fk_constraint_authorToErsUsers
FOREIGN KEY (reimb_author_id)
REFERENCES ers_users (ers_user_id);

-- resolver id is referring user table's user id 
ALTER TABLE ers_reimbursements
ADD CONSTRAINT fk_constraint_resolverToErsUsers
FOREIGN KEY (reimb_resolver_id)
REFERENCES ers_users (ers_user_id);

/************************************************************
 * 			CREATE FUNCTIONS AND STORED PROCEDURES
 ************************************************************/

/************************************************************
 * 			INSERT INITIAL DATA INTO TABLES
 ************************************************************/
--ers_user_roles table data
INSERT INTO ers_user_roles (ers_role_name) VALUES ('EMPLOYEE');
INSERT INTO ers_user_roles (ers_role_name) VALUES ('FINANCE MANAGER');

--ers_users table data
INSERT INTO ers_users (ers_user_role_id, ers_username, ers_user_password, ers_user_first_name, ers_user_last_name, ers_user_email, ers_user_hire_date)
VALUES (1, 'employee001', 'password', 'John', 'Smith', 'jsmith@gmail.com', '2020-10-26');
INSERT INTO ers_users (ers_user_role_id, ers_username, ers_user_password, ers_user_first_name, ers_user_last_name, ers_user_email, ers_user_hire_date)
VALUES (1, 'employee002', 'password', 'Steve', 'Harvey', 'sharvey@gmail.com', '2019-01-18');
INSERT INTO ers_users (ers_user_role_id, ers_username, ers_user_password, ers_user_first_name, ers_user_last_name, ers_user_email, ers_user_hire_date)
VALUES (2, 'aaknox', 'password', 'Azhya', 'Knox', 'azhya.knox@gmail.com', '2018-12-18');

-- ers_reimbursements_types table data
INSERT INTO ers_reimbursements_types(type_name) VALUES ('LODGING');
INSERT INTO ers_reimbursements_types(type_name) VALUES ('TRAVEL');
INSERT INTO ers_reimbursements_types(type_name) VALUES ('FOOD');
INSERT INTO ers_reimbursements_types(type_name) VALUES ('OTHER');

-- ers_reimbursements_status table data
INSERT INTO ers_reimbursements_status(status_name) VALUES ('APPROVED');
INSERT INTO ers_reimbursements_status(status_name) VALUES ('PENDING');
INSERT INTO ers_reimbursements_status(status_name) VALUES ('DENIED');

-- ers_reimbursements table data
INSERT INTO ers_reimbursements VALUES (nextval('reimb_sequence'), 100.50, '2020-04-01 14:25:06', NULL, 'for company cruise trip', 'myreceipt.jpg', 1, NULL, 2, 2);
INSERT INTO ers_reimbursements VALUES (nextval('reimb_sequence'), 250.00, '2020-06-26 19:30:26', NULL, 'for 2020 devops convention', 'hotel-receipt.png', 2, NULL, 2, 1);
INSERT INTO ers_reimbursements VALUES (nextval('reimb_sequence'), 600.75, '2020-09-14 09:47:33', NULL, 'for crazy office party', 'xxxreceipt.jpg', 1, NULL, 2, 4);

/************************************************************
 * 			SELECT ALL STATEMENTS
 ************************************************************/
SELECT * FROM ers_users ORDER BY ers_user_id;
SELECT * FROM ers_user_roles ORDER BY ers_role_id;
SELECT * FROM ers_reimbursements ORDER BY reimb_id;
SELECT * FROM ers_reimbursements_status ORDER BY status_id;
SELECT * FROM ers_reimbursements_types ORDER BY type_id;
/*******************************************************************************
 * 			UPDATE/SELECT/DELETE STATEMENTS (COMMENTED OUT - FOR TESTING ONLY)
 *******************************************************************************/
--DELETE FROM ers_users WHERE ers_user_id = 7;
--UPDATE ers_users SET ers_user_password = 'password' WHERE ers_username = 'aaknox';
--DELETE FROM ers_reimbursements WHERE reimb_id = 400;

/************************************************************
 * 							END OF SCRIPT                   *
 ************************************************************/