-- Clean up
DROP TABLE IF EXISTS "data"."country";
DROP SEQUENCE IF EXISTS "data"."country_id_seq";

-- ---------------------- COUNTRY ------------------------

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










