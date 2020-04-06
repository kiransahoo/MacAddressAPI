
A Restful API to query the mac address of vendors

Tested with
* Docker 19.03.8
* Java 8 or Java 11
* Spring Boot 2.2.4.RELEASE



##Create your own self signed SSL certificate
To get SSL digital certificate for our application we have two options –

to create a self-signed certificate
to obtain SSL certificate from certification authority(CA) we call it CA certificate.
For today’s demo purpose we will create self-signed certificate generated by java keytool command. We need to run the keytool -genkey command from command prompt.

Here is the exact command we will use –

keytool -genkey -alias selfsigned_localhost_sslserver -keyalg RSA -keysize 2048 -validity 700 -keypass changeit -storepass changeit -keystore ssl-server.jks

Make sure the certificate is in the classpath

## Run instructions
```bash
$ git clone 
$ cd MacAddressAPI
$ mvn clean install
$ java -jar target/macaddressapi-web.jar

  access http://localhost:8443

## Docker instructions

// create a docker image
$ docker build -t macaddresslookupapi:1.0 .
// run it
$ sudo docker run -p 8443:8443 macaddresslookupapi:1.0

  access http://localhost:8443
```