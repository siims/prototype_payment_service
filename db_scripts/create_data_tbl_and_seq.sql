----------------------------------------------------------------------
-- cleanup
----------------------------------------------------------------------
-- drop sequence is important
DROP TABLE IF EXISTS "data"."shop";
DROP SEQUENCE IF EXISTS "data"."shop_id_seq";
DROP TABLE IF EXISTS "data"."merchant";
DROP SEQUENCE IF EXISTS "data"."merchant_id_seq";
DROP TABLE IF EXISTS "data"."fin_company";
DROP SEQUENCE IF EXISTS "data"."fin_company_id_seq";
DROP TABLE IF EXISTS "data"."fin_concern";
DROP SEQUENCE IF EXISTS "data"."fin_concern_id_seq";
DROP TABLE IF EXISTS "data"."company";
DROP SEQUENCE IF EXISTS "data"."company_id_seq";

-- drop sequence is NOT important
DROP TABLE IF EXISTS "data"."consumer";
DROP SEQUENCE IF EXISTS "data"."consumer_id_seq";

----------------------------------------------------------------------
------------------------- company ------------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."company_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."company" (
  "id" int8 DEFAULT nextval('"data".company_id_seq'::regclass) NOT NULL,
  "country_id" int4,
  "registration_number" varchar(50) COLLATE "default" NOT NULL,
  "name_from_register" varchar(1000) COLLATE "default" NOT NULL,
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."company" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."company" ADD UNIQUE ("country_id", "registration_number"); -- update needed when company register name changes

-- Foreign Key structure
ALTER TABLE "data"."company" ADD FOREIGN KEY ("country_id") REFERENCES "data"."country" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;


----------------------------------------------------------------------
------------------------- merchant -----------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."merchant_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."merchant" (
  "id" int8 DEFAULT nextval('"data".merchant_id_seq'::regclass) NOT NULL,
  "company_id" int4,
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."merchant" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."merchant" ADD UNIQUE ("company_id");

-- Foreign Key structure
ALTER TABLE "data"."merchant" ADD FOREIGN KEY ("company_id") REFERENCES "data"."company" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;


----------------------------------------------------------------------
------------------------- shop ---------------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."shop_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."shop" (
  "id" int8 DEFAULT nextval('"data".shop_id_seq'::regclass) NOT NULL,
  "merchant_id" int4,
  "normalized_name" varchar(50) COLLATE "default" NOT NULL,
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."shop" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."shop" ADD UNIQUE ("merchant_id", "normalized_name");

-- Foreign Key structure
ALTER TABLE "data"."shop" ADD FOREIGN KEY ("merchant_id") REFERENCES "data"."merchant" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;


----------------------------------------------------------------------
------------------------- fin_concern --------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."fin_concern_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."fin_concern" (
  "id" int8 DEFAULT nextval('"data".fin_concern_id_seq'::regclass) NOT NULL,
  "company_id" int4,
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."fin_concern" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."fin_concern" ADD UNIQUE ("company_id");

-- Foreign Key structure
ALTER TABLE "data"."fin_concern" ADD FOREIGN KEY ("company_id") REFERENCES "data"."company" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;


----------------------------------------------------------------------
------------------------- fin_company -----------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."fin_company_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."fin_company" (
  "id" int8 DEFAULT nextval('"data".fin_company_id_seq'::regclass) NOT NULL,
  "company_id" int4,
  "fin_concern_id" int4,
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."fin_company" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."fin_company" ADD UNIQUE ("company_id", "fin_concern_id");

-- Foreign Key structure
ALTER TABLE "data"."fin_company" ADD FOREIGN KEY ("company_id") REFERENCES "data"."company" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."fin_company" ADD FOREIGN KEY ("fin_concern_id") REFERENCES "data"."fin_concern" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;


----------------------------------------------------------------------
------------------------- consumer -----------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."consumer_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."consumer" (
  "id" int8 DEFAULT nextval('"data".consumer_id_seq'::regclass) NOT NULL,
  "name" varchar(1000) COLLATE "default",
  "account_number" varchar(50) COLLATE "default",
  "fin_company_id" int8 NOT NULL,
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);


