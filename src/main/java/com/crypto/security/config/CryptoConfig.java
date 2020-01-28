package com.crypto.security.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CryptoConfig {
	
	private static final String ALIAS = "demo";
	private static final String PASSWORD = "password";
	
	@Bean("keyStore")
	public KeyStore getKeyStore() {
		KeyStore keyStore = null;
		try(InputStream inputStream = new FileInputStream("/home/ajay/Desktop/demo.jks")) {
			keyStore = KeyStore.getInstance("JKS");
			keyStore.load(inputStream, PASSWORD.toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyStore;
	}
	
	@Bean("privateKey")
	public PrivateKey getPrivateKey(@Qualifier("keyStore") KeyStore keyStore) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
		return (PrivateKey) keyStore.getKey(ALIAS, PASSWORD.toCharArray());
	}
	
	@Bean("publicKey")
	public PublicKey getPublicKey(@Qualifier("keyStore") KeyStore keyStore) throws KeyStoreException {
		return (PublicKey) keyStore.getCertificate(ALIAS).getPublicKey();
	}
}
