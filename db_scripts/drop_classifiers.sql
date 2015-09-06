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