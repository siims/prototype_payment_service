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
  VALUES (-1, 'UNKNOWN', 'UNKNOWN');

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
-- payment_method
INSERT INTO "data"."payment_method" (id, fin_service_id, company_id, receiving_bank_account_id)
  VALUES (1, 1, 1, 1);
INSERT INTO "data"."payment_method" (id, fin_service_id, company_id, receiving_bank_account_id)
  VALUES (2, 1, 1, 2);
INSERT INTO "data"."payment_method" (id, fin_service_id, company_id, receiving_bank_account_id)
  VALUES (3, 1, 3, 3);
INSERT INTO "data"."payment_method" (id, fin_service_id, company_id, receiving_bank_account_id)
  VALUES (4, 2, 1, 4);
INSERT INTO "data"."payment_method" (id, fin_service_id, company_id, receiving_bank_account_id)
  VALUES (5, 2, 3, 4);
INSERT INTO "data"."payment_method" (id, fin_service_id, company_id, receiving_bank_account_id)
  VALUES (6, 3, 1, 1);
INSERT INTO "data"."payment_method" (id, fin_service_id, company_id, receiving_bank_account_id)
  VALUES (7, 3, 1, 2);
INSERT INTO "data"."payment_method" (id, fin_service_id, company_id, receiving_bank_account_id)
  VALUES (8, 3, 3, 3);

-- merchant_existing_payment_method
INSERT INTO "data"."merchant_existing_payment_method" (id, merchant_id, payment_method_id)
  VALUES (1, 1, 1);
INSERT INTO "data"."merchant_existing_payment_method" (id, merchant_id, payment_method_id)
  VALUES (2, 2, 2);
INSERT INTO "data"."merchant_existing_payment_method" (id, merchant_id, payment_method_id)
  VALUES (3, 3, 3);
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
  VALUES (2, 2, -1, false);
INSERT INTO "data"."transaction" (id, order_id, consumer_id, active)
  VALUES (3, 2, -1, false);
INSERT INTO "data"."transaction" (id, order_id, consumer_id, active)
  VALUES (4, 2, -1, false);
INSERT INTO "data"."transaction" (id, order_id, consumer_id, active)
  VALUES (5, 2, -1, false);
INSERT INTO "data"."transaction" (id, order_id, consumer_id)
  VALUES (6, 2, 1);
INSERT INTO "data"."transaction" (id, order_id, consumer_id)
  VALUES (7, 3, -1);
INSERT INTO "data"."transaction" (id, order_id, consumer_id)
  VALUES (8, 5, -1);