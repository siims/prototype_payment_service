----------------------------------------------------------------------
-- cleanup
----------------------------------------------------------------------
-- drop sequence is important

DROP TABLE IF EXISTS "data"."transaction";
DROP SEQUENCE IF EXISTS "data"."transaction_id_seq";
DROP TABLE IF EXISTS "data"."account";
DROP SEQUENCE IF EXISTS "data"."account_id_seq";
DROP TABLE IF EXISTS "data"."order";
DROP SEQUENCE IF EXISTS "data"."order_id_seq";

DROP TABLE IF EXISTS "data"."fee";
DROP SEQUENCE IF EXISTS "data"."fee_id_seq";
DROP TABLE IF EXISTS "data"."payment_method";
DROP SEQUENCE IF EXISTS "data"."payment_method_id_seq";
DROP TABLE IF EXISTS "data"."crypt_key";
DROP SEQUENCE IF EXISTS "data"."crypt_key_id_seq";
DROP TABLE IF EXISTS "data"."bank_account";
DROP SEQUENCE IF EXISTS "data"."bank_account_id_seq";
DROP TABLE IF EXISTS "data"."other_fin_service_to_our";
DROP SEQUENCE IF EXISTS "data"."other_fin_service_to_our_id_seq";
DROP TABLE IF EXISTS "data"."fin_service";
DROP SEQUENCE IF EXISTS "data"."fin_service_id_seq";
DROP TABLE IF EXISTS "data"."fin_company";
DROP SEQUENCE IF EXISTS "data"."fin_company_id_seq";
DROP TABLE IF EXISTS "data"."fin_concern";
DROP SEQUENCE IF EXISTS "data"."fin_concern_id_seq";
DROP TABLE IF EXISTS "data"."contact";
DROP SEQUENCE IF EXISTS "data"."contact_id_seq";
DROP TABLE IF EXISTS "data"."merchant";
DROP SEQUENCE IF EXISTS "data"."merchant_id_seq";
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
------------------------- contact ---------------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."contact_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."contact" (
  "id" int8 DEFAULT nextval('"data".contact_id_seq'::regclass) NOT NULL,
  "company_id" int8,
  "contact_type_id" int8,
  "value" varchar(1000) COLLATE "default" NOT NULL,
  "description" varchar(1000) COLLATE "default" NOT NULL,
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."contact" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."contact" ADD UNIQUE ("company_id", "contact_type_id", "value");

-- Foreign Key structure
ALTER TABLE "data"."contact" ADD FOREIGN KEY ("company_id") REFERENCES "data"."company" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."contact" ADD FOREIGN KEY ("contact_type_id") REFERENCES "data"."contact_type" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;


----------------------------------------------------------------------
------------------------- merchant ---------------------------------------
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
  "company_id" int8,
  "normalized_name" varchar(50) COLLATE "default" NOT NULL,
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."merchant" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."merchant" ADD UNIQUE ("company_id", "normalized_name");

-- Foreign Key structure
ALTER TABLE "data"."merchant" ADD FOREIGN KEY ("company_id") REFERENCES "data"."company" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;


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
  "company_id" int8,
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
  "company_id" int8,
  "fin_concern_id" int8,
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
------------------------- fin_service --------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."fin_service_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."fin_service" (
  "id" int8 DEFAULT nextval('"data".fin_service_id_seq'::regclass) NOT NULL,
  "fin_company_id" int8,
  "fin_service_type_id" int8,
  "name" varchar(50) COLLATE "default" NOT NULL,
  "description" varchar(1000) COLLATE "default" NOT NULL,
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."fin_service" ADD PRIMARY KEY ("id");

-- Foreign Key structure
ALTER TABLE "data"."fin_service" ADD FOREIGN KEY ("fin_company_id") REFERENCES "data"."fin_company" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."fin_service" ADD FOREIGN KEY ("fin_service_type_id") REFERENCES "data"."fin_service_type" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;


----------------------------------------------------------------------
------------------------- other_fin_service_to_our -------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."other_fin_service_to_our_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."other_fin_service_to_our" (
  "id" int8 DEFAULT nextval('"data".other_fin_service_to_our_id_seq'::regclass) NOT NULL,
  "other_fin_service_id" int8,
  "our_alternative_fin_service_id" int8,
  "valid_till_date" timestamp(6),
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."other_fin_service_to_our" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."other_fin_service_to_our" ADD UNIQUE ("other_fin_service_id", "our_alternative_fin_service_id"); -- TODO: non overlapping period

-- Foreign Key structure
ALTER TABLE "data"."other_fin_service_to_our" ADD FOREIGN KEY ("other_fin_service_id") REFERENCES "data"."fin_service" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."other_fin_service_to_our" ADD FOREIGN KEY ("our_alternative_fin_service_id") REFERENCES "data"."fin_service" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;


----------------------------------------------------------------------
------------------------- bank_account -------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."bank_account_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."bank_account" (
  "id" int8 DEFAULT nextval('"data".bank_account_id_seq'::regclass) NOT NULL,
  "company_id" int8,
  "account_number" varchar(50) COLLATE "default" NOT NULL,
  "currency_id" int4,
  "fin_company_id" int8,
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."bank_account" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."bank_account" ADD UNIQUE ("company_id", "account_number", "currency_id");

-- Foreign Key structure
ALTER TABLE "data"."bank_account" ADD FOREIGN KEY ("company_id") REFERENCES "data"."company" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."bank_account" ADD FOREIGN KEY ("currency_id") REFERENCES "data"."currency" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."bank_account" ADD FOREIGN KEY ("fin_company_id") REFERENCES "data"."fin_company" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

----------------------------------------------------------------------
------------------------- crypt_key ----------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."crypt_key_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."crypt_key" (
  "id" int8 DEFAULT nextval('"data".crypt_key_id_seq'::regclass) NOT NULL,
  "company_id" int8,
  "key_alias" varchar(50) COLLATE "default" NOT NULL,
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."crypt_key" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."crypt_key" ADD UNIQUE ("company_id", "key_alias");

-- Foreign Key structure
ALTER TABLE "data"."crypt_key" ADD FOREIGN KEY ("company_id") REFERENCES "data"."company" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;


----------------------------------------------------------------------
------------------------- payment_method --------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."payment_method_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."payment_method" (
  "id" int8 DEFAULT nextval('"data".payment_method_id_seq'::regclass) NOT NULL,
  "fin_service_id" int8,
  "merchant_id" int8,
  "receiving_bank_account_id" int8,
  "name" varchar(50) COLLATE "default",
  "fin_company_service_key_id" int8,
  "company_key_id" int8,
  "contact_name_id" int4,
  "contact_phone_id" int4,
  "contact_email_id" int4,
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."payment_method" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."payment_method" ADD UNIQUE ("fin_service_id", "merchant_id", "receiving_bank_account_id");

-- Foreign Key structure
ALTER TABLE "data"."payment_method" ADD FOREIGN KEY ("fin_service_id") REFERENCES "data"."fin_service" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."payment_method" ADD FOREIGN KEY ("merchant_id") REFERENCES "data"."merchant" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."payment_method" ADD FOREIGN KEY ("receiving_bank_account_id") REFERENCES "data"."bank_account" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION; -- FIXME
ALTER TABLE "data"."payment_method" ADD FOREIGN KEY ("fin_company_service_key_id") REFERENCES "data"."crypt_key" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."payment_method" ADD FOREIGN KEY ("company_key_id") REFERENCES "data"."crypt_key" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."payment_method" ADD FOREIGN KEY ("contact_name_id") REFERENCES "data"."contact" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."payment_method" ADD FOREIGN KEY ("contact_phone_id") REFERENCES "data"."contact" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."payment_method" ADD FOREIGN KEY ("contact_email_id") REFERENCES "data"."contact" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;


----------------------------------------------------------------------
------------------------- fee ---------------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."fee_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."fee" (
  "id" int8 DEFAULT nextval('"data".fee_id_seq'::regclass) NOT NULL,
  "fin_service_id" int8,
  "fee_type_id" int4,
  "amount" numeric(28,5) DEFAULT 0 NOT NULL,
  "description" varchar(1000) COLLATE "default",
  "valid_from_date" timestamp(6) DEFAULT now() NOT NULL,
  "valid_till_date" timestamp(6),
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."fee" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."fee" ADD UNIQUE ("fin_service_id", "fee_type_id", "valid_from_date");
-- TODO: add unique inclusive periods

-- Foreign Key structure
ALTER TABLE "data"."fee" ADD FOREIGN KEY ("fin_service_id") REFERENCES "data"."fin_service" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."fee" ADD FOREIGN KEY ("fee_type_id") REFERENCES "data"."fee_type" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Extra constraints
ALTER TABLE "data"."fee" ADD CHECK ("valid_from_date" < "valid_till_date"); -- FIXME: handle situations where till date is not given


----------------------------------------------------------------------
------------------------- account ---------------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."account_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."account" (
  "id" int8 DEFAULT nextval('"data".account_id_seq'::regclass) NOT NULL,
  "merchant_id" int8,
  "currency_id" int4,
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL,
  "closed_date" timestamp(6)
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."account" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."account" ADD UNIQUE ("merchant_id", "currency_id"); -- TODO: add period from created to closed.


-- Foreign Key structure
ALTER TABLE "data"."account" ADD FOREIGN KEY ("merchant_id") REFERENCES "data"."merchant" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."account" ADD FOREIGN KEY ("currency_id") REFERENCES "data"."currency" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;




----------------------------------------------------------------------
------------------------- consumer -----------------------------------
----------------------------------------------------------------------

 -- TODO: can we know fin_company_id?

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
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."consumer" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."consumer" ADD UNIQUE ("name", "account_number");

----------------------------------------------------------------------
------------------------------- order --------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."order_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."order" (
  "id" int8 DEFAULT nextval('"data".order_id_seq'::regclass) NOT NULL,
  "merchant_id" int8,
  "merchants_order_name" varchar(1000) COLLATE "default" NOT NULL,
  "currency_id" int4,
  "amount" numeric(28,5) DEFAULT 0 NOT NULL,
  "reference_number" int8,
  "explanation" varchar(1000) COLLATE "default",
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."order" ADD PRIMARY KEY ("id");

-- Uniques structure
ALTER TABLE "data"."order" ADD UNIQUE ("merchant_id", "merchants_order_name");

-- Foreign Key structure
ALTER TABLE "data"."order" ADD FOREIGN KEY ("merchant_id") REFERENCES "data"."merchant" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."order" ADD FOREIGN KEY ("currency_id") REFERENCES "data"."currency" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;


----------------------------------------------------------------------
------------------------- transaction --------------------------------
----------------------------------------------------------------------

-- Sequence structure
CREATE SEQUENCE "data"."transaction_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table structure
CREATE TABLE "data"."transaction" (
  "id" int8 DEFAULT nextval('"data".transaction_id_seq'::regclass) NOT NULL,
  "order_id" int8,
  "consumer_id" int8,
  "transaction_state" varchar(50) COLLATE "default" DEFAULT 'UNDEFINED',
  "fund_state" varchar(50) COLLATE "default" DEFAULT 'UNDEFINED',
  "description" varchar(1000) COLLATE "default",
  "active" bool DEFAULT true NOT NULL,
  "created_date" timestamp(6) DEFAULT now() NOT NULL,
  "modified_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE);

-- Primary Key structure
ALTER TABLE "data"."transaction" ADD PRIMARY KEY ("id");

-- Foreign Key structure
ALTER TABLE "data"."transaction" ADD FOREIGN KEY ("order_id") REFERENCES "data"."order" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "data"."transaction" ADD FOREIGN KEY ("consumer_id") REFERENCES "data"."consumer" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;



