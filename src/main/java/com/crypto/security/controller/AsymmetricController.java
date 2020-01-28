package com.crypto.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.security.model.Crypto;
import com.crypto.security.service.AsymmetricService;

@RestController
@RequestMapping("/crypto")
public class AsymmetricController {

	@Autowired
	private AsymmetricService asymmetricService;
	
	@GetMapping("/encrypt/{input}")
	public String encrypt(@PathVariable("input") String input) {
		return asymmetricService.encrypt(input);
	}
	
	@PostMapping("/decrypt")
	public String decrypt(@RequestBody Crypto input) {
		return asymmetricService.decrypt(input.getEncryptedString());
	}
}
