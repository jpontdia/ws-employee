CREATE  TABLE "public".employee (
	id                   serial  NOT NULL ,
	first_name           varchar(100)  NOT NULL ,
	last_name            varchar(100)  NOT NULL ,
	birth_date           date  NOT NULL ,
	hire_date            date  NOT NULL ,
	gender               varchar(1)  NOT NULL ,
	status               char(1)  NOT NULL ,
	CONSTRAINT pk_employees_employee_number PRIMARY KEY ( id )
 );

COMMENT ON COLUMN "public".employee.gender IS 'Masculine/Feminine';

COMMENT ON COLUMN "public".employee.status IS 'Active/Inactive/Leave/Terminated';

CREATE  TABLE "public".salary (
	employee             integer  NOT NULL ,
	from_date            date  NOT NULL ,
	to_date              date   ,
	salary               numeric  NOT NULL ,
	CONSTRAINT salaries_pk PRIMARY KEY ( employee, from_date )
 );

CREATE  TABLE "public".address (
	employee             integer  NOT NULL ,
	from_date            date  NOT NULL ,
	to_date              date   ,
	address              varchar(150)  NOT NULL ,
	CONSTRAINT pk_addresses_from_date PRIMARY KEY ( employee, from_date )
 );

ALTER TABLE "public".address ADD CONSTRAINT fk_addresses_employees FOREIGN KEY ( employee ) REFERENCES "public".employee( id );

ALTER TABLE "public".salary ADD CONSTRAINT fk_salaries_employees FOREIGN KEY ( employee ) REFERENCES "public".employee( id );
