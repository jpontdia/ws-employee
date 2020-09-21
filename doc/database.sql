CREATE  TABLE "public".employees ( 
	employee_number      serial  NOT NULL ,
	first_name           varchar(100)  NOT NULL ,
	last_name            varchar(100)  NOT NULL ,
	birth_date           date  NOT NULL ,
	hire_date            date  NOT NULL ,
	gender               varchar(1)  NOT NULL ,
	status               char(1)  NOT NULL ,
	CONSTRAINT pk_employees_employee_number PRIMARY KEY ( employee_number )
 );

COMMENT ON COLUMN "public".employees.gender IS 'Masculine/Feminine';

COMMENT ON COLUMN "public".employees.status IS 'Active/Inactive/Leave/Terminated';

CREATE  TABLE "public".salaries ( 
	employee_number      integer  NOT NULL ,
	from_date            date  NOT NULL ,
	to_date              date   ,
	salary               numeric  NOT NULL ,
	CONSTRAINT salaries_pk PRIMARY KEY ( employee_number, from_date )
 );

CREATE  TABLE "public".addresses ( 
	employee_number      integer  NOT NULL ,
	from_date            date  NOT NULL ,
	to_date              date   ,
	address              varchar(150)  NOT NULL ,
	CONSTRAINT pk_addresses_from_date PRIMARY KEY ( employee_number, from_date )
 );

ALTER TABLE "public".addresses ADD CONSTRAINT fk_addresses_employees FOREIGN KEY ( employee_number ) REFERENCES "public".employees( employee_number );

ALTER TABLE "public".salaries ADD CONSTRAINT fk_salaries_employees FOREIGN KEY ( employee_number ) REFERENCES "public".employees( employee_number );
