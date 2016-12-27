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
DROP TABLE IF EXISTS "data"."merchant_crypt_key";
DROP SEQUENCE IF EXISTS "data"."merchant_crypt_key_id_seq";
DROP TABLE IF EXISTS "data"."fin_service_pub_key";
DROP SEQUENCE IF EXISTS "data"."fin_service_pub_key_id_seq";
DROP TABLE IF EXISTS "data"."fin_service_image";
DROP SEQUENCE IF EXISTS "data"."fin_service_image_id_seq";
DROP TABLE IF EXISTS "data"."merchant_url";
DROP SEQUENCE IF EXISTS "data"."merchant_url_id_seq";
DROP TABLE IF EXISTS "data"."bank_account";
DROP SEQUENCE IF EXISTS "data"."bank_account_id_seq";
DROP TABLE IF EXISTS "data"."unique_fin_service";
DROP SEQUENCE IF EXISTS "data"."unique_fin_service_id_seq";
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
