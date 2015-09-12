-- NOTE! it is assumed that tables are empty and sequences are reset

-- classificators
INSERT INTO "data"."country" (id, name, description) VALUES (1, 'EST', 'Estonia');
INSERT INTO "data"."currency" (id, name, description) VALUES (1, 'EUR', 'euro');
INSERT INTO "data"."contact_type" (id, name, description) VALUES (1, 'FULL_NAME', 'company/person full name');
INSERT INTO "data"."contact_type" (id, name, description) VALUES (2, 'EMAIL', 'email');
INSERT INTO "data"."contact_type" (id, name, description) VALUES (3, 'TEL', 'telephone number');
INSERT INTO "data"."fin_service_type" (id, name, description) VALUES (1, 'VK_BANK_LINK', 'VK standard banklink');
INSERT INTO "data"."fee_type" (id, name, description) VALUES (1, 'FIXED', 'fixed amount');
INSERT INTO "data"."fee_type" (id, name, description) VALUES (2, 'PERCENTAGE', 'percentage from total amount');

-- some unknown values:
INSERT INTO "data"."consumer" (id, name, account_number)
  VALUES (0, 'UNKNOWN', 'UNKNOWN');

-- company
INSERT INTO "data"."company" (id, country_id, registration_number, name_from_register)
  VALUES (0, 1, '0000', 'Our Company');
INSERT INTO "data"."company" (id, country_id, registration_number, name_from_register)
  VALUES (1, 1, '1234', 'Test Kaupmehe Ettevõte OÜ');
INSERT INTO "data"."company" (id, country_id, registration_number, name_from_register)
  VALUES (2, 1, '4321', 'Test Panga Ettevõte aktsiaselts');
INSERT INTO "data"."company" (id, country_id, registration_number, name_from_register)
  VALUES (3, 1, '123456789', 'Firma 2 Test OÜ');
INSERT INTO "data"."company" (id, country_id, registration_number, name_from_register)
  VALUES (4, 1, '987654321', 'SEB aktsiaselts');
-- merchant
INSERT INTO "data"."merchant" (id, company_id, normalized_name)
  VALUES (1, 1, 'Beebitooded24');
INSERT INTO "data"."merchant" (id, company_id, normalized_name)
  VALUES (2, 1, 'Autovaruosad24');
INSERT INTO "data"."merchant" (id, company_id, normalized_name)
  VALUES (3, 3, '24/7 autovaruosad');
-- merchant's account
INSERT INTO "data"."account" (id, merchant_id, currency_id)
  VALUES (1, 1, 1);
INSERT INTO "data"."account" (id, merchant_id, currency_id)
  VALUES (2, 2, 1);
INSERT INTO "data"."account" (id, merchant_id, currency_id)
  VALUES (3, 3, 1);

-- fin company
INSERT INTO "data"."fin_concern" (id, company_id)
  VALUES (0, 0);
INSERT INTO "data"."fin_company" (id, company_id, fin_concern_id)
  VALUES (0, 0, 0);
INSERT INTO "data"."fin_concern" (id, company_id)
  VALUES (1, 2);
INSERT INTO "data"."fin_company" (id, company_id, fin_concern_id)
  VALUES (1, 2, 1);
INSERT INTO "data"."fin_concern" (id, company_id)
  VALUES (2, 4);
INSERT INTO "data"."fin_company" (id, company_id, fin_concern_id)
  VALUES (2, 4, 2);

-- bank_account
INSERT INTO "data"."bank_account" (id, company_id, account_number, currency_id, fin_company_id)
  VALUES (1, 1, 'EE0123456789BEEBI', 1, 1);
INSERT INTO "data"."bank_account" (id, company_id, account_number, currency_id, fin_company_id)
  VALUES (2, 1, 'EE0123456789AUTO', 1, 1);
INSERT INTO "data"."bank_account" (id, company_id, account_number, currency_id, fin_company_id)
  VALUES (3, 3, 'BIC1234567890', 1, 1);
INSERT INTO "data"."bank_account" (id, company_id, account_number, currency_id, fin_company_id)
  VALUES (4, 0, 'EE0000 TPE', 1, 1);
INSERT INTO "data"."bank_account" (id, company_id, account_number, currency_id, fin_company_id)
  VALUES (5, 0, 'EE0000 SEB', 1, 2);

-- fin_service
INSERT INTO "data"."fin_service" (id, fin_company_id, fin_service_type_id, name, description)
  VALUES (1, 1, 1, 'TPE bank link', 'dull corporate bank link');
INSERT INTO "data"."fin_service" (id, fin_company_id, fin_service_type_id, name, description)
  VALUES (2, 0, 1, 'Our Banklink to TPE bank', 'onepay - super awesome way to pay');
INSERT INTO "data"."fin_service" (id, fin_company_id, fin_service_type_id, name, description)
  VALUES (3, 0, 1, 'Our Banklink to SEB bank', 'onepay - super awesome way to pay');
INSERT INTO "data"."fin_service" (id, fin_company_id, fin_service_type_id, name, description)
  VALUES (4, 2, 1, 'SEB bank link', 'dull corporate bank link');

-- mapping between other fin services and our fin service
INSERT INTO "data"."unique_fin_service" (id, other_fin_service_id, our_alternative_fin_service_id)
  VALUES (1, 1, 2);
INSERT INTO "data"."unique_fin_service" (id, other_fin_service_id, our_alternative_fin_service_id)
  VALUES (2, 4, 3);
  
-- merchant_crypt_key -- TODO
INSERT INTO "data"."merchant_crypt_key" (id, merchant_id, key_alias)
  VALUES (1, 1, 1);
  
-- fin_service_pub_key -- TODO
INSERT INTO "data"."fin_service_pub_key" (id, fin_company_id, pub_key)
  VALUES (1, 1, '-----BEGIN CERTIFICATE-----\nMIIDljCCAn4CCQCBmddvYTNKbTANBgkqhkiG9w0BAQUFADCBjDELMAkGA1UEBhMCRUUxETAPBgNVBAgTCEhhcmp1bWFhMRAwDgYDVQQHEwdUYWxsaW5uMQ0wCwYDVQQKEwRUZXN0MREwDwYDVQQLEwhiYW5rbGluazEXMBUGA1UEAxMObG9jYWxob3N0IDgwODExHTAbBgkqhkiG9w0BCQEWDnRlc3RAbG9jYWxob3N0MB4XDTE1MDgzMTExNDExNVoXDTM1MDgyNjExNDExNVowgYwxCzAJBgNVBAYTAkVFMREwDwYDVQQIEwhIYXJqdW1hYTEQMA4GA1UEBxMHVGFsbGlubjENMAsGA1UEChMEVGVzdDERMA8GA1UECxMIYmFua2xpbmsxFzAVBgNVBAMTDmxvY2FsaG9zdCA4MDgxMR0wGwYJKoZIhvcNAQkBFg50ZXN0QGxvY2FsaG9zdDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBANfS/2Z5c/1PBveT0RvM/TA162oumn8P7ecG0mfM2FI7i2uAtXlluj0fMLj8Slw6xO2y6SmTLkq31XoO+hDy10gp7OhKZLhyaSS1ueFO1ef/j7gGvbipXhxPS6HToyI06NbaSel206eP+NrbF+DvZ76RY42bkAScN5Y8AGGUdQUX1OPVOgbv7LO+3sUfQNBDMwxy/y+rCI0AHYR0bKtas7h18TS6WpDnj7TZCPTJIX1jUO5pWCvPsi4t3k/yiUAEALEpn//qZhdR+lgP5n0SNo63SWTkpgT+qnJ2vrycLJTJ7I+G/5BPKtMZoKHOohILrqBPP8Bhmrx9hF4UXVlZXYcCAwEAATANBgkqhkiG9w0BAQUFAAOCAQEAwD3Iis24RLRKO6CehOXB31Id4Pe+jz3PyFNJjC4mM7beYcpbDVRX4TMb5BfBiYgK80y417jT1k3Wsi4HwOSkU+bANiKjE0YJRLx8uNCV0XkFv2clz7saJq0OgCqs2+8hxlm6tiDoU3JowVeadCkU4gnzZlaLlviHLVd+zVdwLQM8VPm+0Y5H2stveq0Yj5t2ekvsSxoYiXD1Aga2plZBWUp4bgGVOr1uIkV8IoboVbe6A4D5QBakXAucioizighVv7zJGQ/Ir0HbP02G6AbcerVs4K3mMTkwgAlhFVjJtyAKd0QvA86XQovB9TaGWUcX/zwcOVu9G8/zjpVVxvTdfw==-----END CERTIFICATE-----');

-- fin_service_image -- TODO
INSERT INTO "data"."fin_service_image" (id, fin_service_id, image_url)
  VALUES (1, 1, 'http://www.seb.ee/sites/default/files/web/images/logod/seb_88x31.gif');

-- merchant_url -- TODO
INSERT INTO "data"."merchant_url" (id, merchant_id, url)
  VALUES (1, 1, 'http://nomme.eu/onepay.html?return');
INSERT INTO "data"."merchant_url" (id, merchant_id, url)
  VALUES (2, 1, 'http://nomme.eu/onepay.html?cancel');

-- payment_method
INSERT INTO "data"."payment_method" (id, fin_service_id, merchant_id, receiving_bank_account_id, fin_service_pub_key_id, merchant_crypt_key_id, sender_id_in_fin_service, success_url_id, failure_url_id, fin_service_image_id)
  VALUES (1, 1, 1, 2, 1, 1, 'uid100010', 1, 2, 1);
INSERT INTO "data"."payment_method" (id, fin_service_id, merchant_id, receiving_bank_account_id, fin_service_pub_key_id, merchant_crypt_key_id, sender_id_in_fin_service, success_url_id, failure_url_id, fin_service_image_id)
  VALUES (2, 3, 1, 2, 1, 1, 'onepay uid100010', 1, 2, 1);
INSERT INTO "data"."payment_method" (id, fin_service_id, merchant_id, receiving_bank_account_id, fin_service_pub_key_id, merchant_crypt_key_id, sender_id_in_fin_service, success_url_id, failure_url_id, fin_service_image_id)
  VALUES (3, 2, 2, 1, 1, 1, 'random1', 1, 2, 1);
INSERT INTO "data"."payment_method" (id, fin_service_id, merchant_id, receiving_bank_account_id, fin_service_pub_key_id, merchant_crypt_key_id, sender_id_in_fin_service, success_url_id, failure_url_id, fin_service_image_id)
  VALUES (4, 3, 2, 1, 1, 1, 'random2', 1, 2, 1);
INSERT INTO "data"."payment_method" (id, fin_service_id, merchant_id, receiving_bank_account_id, fin_service_pub_key_id, merchant_crypt_key_id, sender_id_in_fin_service, success_url_id, failure_url_id, fin_service_image_id)
  VALUES (5, 1, 3, 3, 1, 1, 'random3', 1, 2, 1);
INSERT INTO "data"."payment_method" (id, fin_service_id, merchant_id, receiving_bank_account_id, fin_service_pub_key_id, merchant_crypt_key_id, sender_id_in_fin_service, success_url_id, failure_url_id, fin_service_image_id)
  VALUES (6, 4, 3, 3, 1, 1, 'random4', 1, 2, 1);

-- fee
INSERT INTO "data"."fee" (id, fin_service_id, fee_type_id, valid_from_date, amount)
  VALUES (1, 1, 1, '1999-01-01', 0.31);
INSERT INTO "data"."fee" (id, fin_service_id, fee_type_id, valid_from_date, amount)
  VALUES (2, 1, 2, '1999-01-01', 1.5);
INSERT INTO "data"."fee" (id, fin_service_id, fee_type_id, valid_from_date, amount)
  VALUES (3, 2, 1, '1999-01-01', 0.35);
INSERT INTO "data"."fee" (id, fin_service_id, fee_type_id, valid_from_date, amount)
  VALUES (4, 2, 2, '1999-01-01', 2.0);
INSERT INTO "data"."fee" (id, fin_service_id, fee_type_id, valid_from_date, amount)
  VALUES (5, 3, 1, '1999-01-01', 0.25);
INSERT INTO "data"."fee" (id, fin_service_id, fee_type_id, valid_from_date, amount)
  VALUES (6, 3, 2, '1999-01-01', 1.9);

-- consumer
INSERT INTO "data"."consumer" (id, name, account_number)
  VALUES (1, 'ALO MARGUS', 'EE1111');
INSERT INTO "data"."consumer" (id, name, account_number)
  VALUES (2, 'TIINA NIGULIS', 'EE2222');

-- order
INSERT INTO "data"."order" (id, merchant_id, merchants_order_name)
  VALUES (1, 1, 'ORDER 1-2009');
INSERT INTO "data"."order" (id, merchant_id, merchants_order_name)
  VALUES (2, 1, 'ORDER 2-2009');
INSERT INTO "data"."order" (id, merchant_id, merchants_order_name)
  VALUES (3, 1, 'ORDER 3-2009');
INSERT INTO "data"."order" (id, merchant_id, merchants_order_name)
  VALUES (4, 1, 'ORDER 4-2009');
INSERT INTO "data"."order" (id, merchant_id, merchants_order_name)
  VALUES (5, 3, 'INVOICE/ORDER 12333');
INSERT INTO "data"."order" (id, merchant_id, merchants_order_name)
  VALUES (6, 2, 'ORDER 5-2009');
INSERT INTO "data"."order" (id, merchant_id, merchants_order_name)
  VALUES (7, 2, 'ORDER 19-2009');

-- transaction
-- todo: add states
INSERT INTO "data"."transaction" (id, order_id, consumer_id)
  VALUES (1, 1, 1);
INSERT INTO "data"."transaction" (id, order_id, consumer_id, active)
  VALUES (2, 2, 0, false);
INSERT INTO "data"."transaction" (id, order_id, consumer_id, active)
  VALUES (3, 2, 0, false);
INSERT INTO "data"."transaction" (id, order_id, consumer_id, active)
  VALUES (4, 2, 0, false);
INSERT INTO "data"."transaction" (id, order_id, consumer_id, active)
  VALUES (5, 2, 0, false);
INSERT INTO "data"."transaction" (id, order_id, consumer_id)
  VALUES (6, 2, 1);
INSERT INTO "data"."transaction" (id, order_id, consumer_id)
  VALUES (7, 3, 0);
INSERT INTO "data"."transaction" (id, order_id, consumer_id)
  VALUES (8, 5, 0);