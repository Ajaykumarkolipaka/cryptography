package com.crypto.security.service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsymmetricService {
	private static final String ALGORITHM = "RSA";
	
	@Autowired
	private PrivateKey privateKey;
	
	@Autowired
	private PublicKey publicKey;

	public String encrypt(String input) {
		String encryptedString = "";
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] encryptedBytes = cipher.doFinal(input.getBytes());
			encryptedString = new String(encode(encryptedBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}
	
	public String decrypt(String input) {
		String decryptedString  = "";
		try {
			byte[] encryptedDecodedBytes = decode(input.getBytes());
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			decryptedString = new String(cipher.doFinal(encryptedDecodedBytes));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return decryptedString;
	}
	
	public byte[] encode(byte[] input) {
		return Base64.getEncoder().encode(input);
	}
	
	public byte[] decode(byte[] input) {
		return Base64.getDecoder().decode(input);
	}
}
