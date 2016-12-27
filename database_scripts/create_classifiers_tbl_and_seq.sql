-- Clean up classifiers
DROP TABLE IF EXISTS "data"."country";
DROP SEQUENCE IF EXISTS "data"."country_id_seq";
DROP TABLE IF EXISTS "data"."currency";
DROP SEQUENCE IF EXISTS "data"."currency_id_seq";
DROP TABLE IF EXISTS "data"."contact_type";
DROP SEQUENCE IF EXISTS "data"."contact_type_id_seq";
DROP TABLE IF EXISTS "data"."fin_service_type";
DROP SEQUENCE IF EXISTS "data"."fin_service_type_id_seq";
DROP TABLE IF EXISTS "data"."fee_type";
DROP SEQUENCE IF EXISTS "data"."fee_type_id_seq";


-- ---------------------- country ---------------------------------

-- ----------------------------
-- Sequence structure for country_id_seq
-- ----------------------------
CREATE SEQUENCE "data"."country_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Table structure for country
-- ----------------------------
CREATE TABLE "data"."country" (
"id" int4 DEFAULT nextval('"data".country_id_seq'::regclass) NOT NULL,
"name" varchar(50) COLLATE "default" NOT NULL,
"description" varchar(1000) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE);

-- ----------------------------
-- Indexes structure for table country
-- ----------------------------
CREATE UNIQUE INDEX "country_in" ON "data"."country" USING btree ("id", "name");

-- ----------------------------
-- Uniques structure for table country
-- ----------------------------
ALTER TABLE "data"."country" ADD UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table country
-- ----------------------------
ALTER TABLE "data"."country" ADD PRIMARY KEY ("id");



-- ---------------------- currency ----------------------------------

-- ----------------------------
-- Sequence structure for currency_id_seq
-- ----------------------------
CREATE SEQUENCE "data"."currency_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Table structure for currency
-- ----------------------------
CREATE TABLE "data"."currency" (
"id" int4 DEFAULT nextval('"data".currency_id_seq'::regclass) NOT NULL,
"name" varchar(50) COLLATE "default" NOT NULL,
"description" varchar(1000) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE);

-- ----------------------------
-- Indexes structure for table currency
-- ----------------------------
CREATE UNIQUE INDEX "currency_in" ON "data"."currency" USING btree ("id", "name");

-- ----------------------------
-- Uniques structure for table currency
-- ----------------------------
ALTER TABLE "data"."currency" ADD UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table currency
-- ----------------------------
ALTER TABLE "data"."currency" ADD PRIMARY KEY ("id");



-- ---------------------- contact_type ---------------------------------

-- ----------------------------
-- Sequence structure for contact_type_id_seq
-- ----------------------------
CREATE SEQUENCE "data"."contact_type_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Table structure for contact_type
-- ----------------------------
CREATE TABLE "data"."contact_type" (
"id" int4 DEFAULT nextval('"data".contact_type_id_seq'::regclass) NOT NULL,
"name" varchar(50) COLLATE "default" NOT NULL,
"description" varchar(1000) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE);

-- ----------------------------
-- Indexes structure for table contact_type
-- ----------------------------
CREATE UNIQUE INDEX "contact_type_in" ON "data"."contact_type" USING btree ("id", "name");

-- ----------------------------
-- Uniques structure for table contact_type
-- ----------------------------
ALTER TABLE "data"."contact_type" ADD UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table contact_type
-- ----------------------------
ALTER TABLE "data"."contact_type" ADD PRIMARY KEY ("id");



-- ---------------------- fin_service_type ---------------------------------

-- ----------------------------
-- Sequence structure for fin_service_type_id_seq
-- ----------------------------
CREATE SEQUENCE "data"."fin_service_type_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Table structure for fin_service_type
-- ----------------------------
CREATE TABLE "data"."fin_service_type" (
"id" int4 DEFAULT nextval('"data".fin_service_type_id_seq'::regclass) NOT NULL,
"name" varchar(50) COLLATE "default" NOT NULL,
"description" varchar(1000) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE);

-- ----------------------------
-- Indexes structure for table fin_service_type
-- ----------------------------
CREATE UNIQUE INDEX "fin_service_type_in" ON "data"."fin_service_type" USING btree ("id", "name");

-- ----------------------------
-- Uniques structure for table fin_service_type
-- ----------------------------
ALTER TABLE "data"."fin_service_type" ADD UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table fin_service_type
-- ----------------------------
ALTER TABLE "data"."fin_service_type" ADD PRIMARY KEY ("id");



-- ---------------------- fee_type ---------------------------------

-- ----------------------------
-- Sequence structure for fee_type_id_seq
-- ----------------------------
CREATE SEQUENCE "data"."fee_type_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Table structure for fee_type
-- ----------------------------
CREATE TABLE "data"."fee_type" (
"id" int4 DEFAULT nextval('"data".fee_type_id_seq'::regclass) NOT NULL,
"name" varchar(50) COLLATE "default" NOT NULL,
"description" varchar(1000) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE);

-- ----------------------------
-- Indexes structure for table fee_type
-- ----------------------------
CREATE UNIQUE INDEX "fee_type_in" ON "data"."fee_type" USING btree ("id", "name");

-- ----------------------------
-- Uniques structure for table fee_type
-- ----------------------------
ALTER TABLE "data"."fee_type" ADD UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table fee_type
-- ----------------------------
ALTER TABLE "data"."fee_type" ADD PRIMARY KEY ("id");

