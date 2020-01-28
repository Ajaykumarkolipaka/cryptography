# cryptography
Implementation of asymmetric encryption

In this project we will encrypt a string using public key and decrypt with private key.
We will create a jks file using keytool command and read public and private keys from jks file and perform asymmetric encryption.

#Genereate jks file using below command
keytool -genkey -alias create_Alias -keyalg RSA -keystore path_and_create_KeystoreFilename.jks -keysize 2048
