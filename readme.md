

** GENERAL **

Dependencies:
* java 8
* Spring
* tomcat 8
* When using eclipse lombok to eclipse



** PAYMENT **

Must often do maven update. Haven't researched why.

Estonian pank payments (and some Finlands)
-download http://pangalink.net/ from here test environment.

-Let it run on port 8081.

-then go to http://localhost:8081/

-'Makeselahendused' in the top menu

-then on the right press the button to add new payment option

-add SEB makeselahendus

-go back to 'makselahendused' view copy "Kasutajatunnus"

-go to 'OurServletContext.java' class and replace the value of "sendersId" with your kasutajatunnus


keyStore password is: 123456
Now you need to add your key to the truststore.ks.

-For that navigate to resource folder.

-take the keys that where generated for you by Pangalink-net.

-then copy and paste the keys to one file eneCert.pem

-Run the command: openssl pkcs12 -export -out eneCert.pkcs12 -in eneCert.pem

-Run the command: keytool -v -importkeystore -srckeystore eneCert.pkcs12 -srcstoretype PKCS12 -destkeystore keystore.ks -deststoretype JKS -alias "seb_key"

-Run the command: keytool -list -v -keystore truststore.ks

-Find out that the key with your alias name is there (in the example its 'seb_key')

-Now replace the alias name in 'OurServletContext.java' - variable name is 'privateKeyAlias' - with the alias you chose.

Should work! :) 

Right now only SEB payment is implemented


** DATABASE **
Database creation files are in onepay_base project. It is also described there how to set database up
Configuration is in src/main/resources/db.resources file