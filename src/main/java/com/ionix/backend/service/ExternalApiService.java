package com.ionix.backend.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ionix.backend.dto.ApiResponse;
import com.ionix.backend.exception.InternalException;
import com.ionix.backend.model.ExternalResponse;
import com.ionix.backend.model.Result;

@Service
public class ExternalApiService {

	@Value("${com.ionix.backend.service.secret}")
	private String secret;

	@Value("${com.ionix.backend.service.url-api}")
	private String url;

	private RestTemplate template;

	public ExternalApiService() {
		template = new RestTemplate();
	}

	public ExternalResponse getExternalResponse(String param) throws InternalException {

		String encodedParam = encodeParam(param);

		long beforeTime = Calendar.getInstance().getTimeInMillis();

		ResponseEntity<ApiResponse> response = new ResponseEntity<>(HttpStatus.OK);

		try {
			response = template.getForEntity(getUrl() + encodedParam, ApiResponse.class);
		} catch (Exception e) {
			throw new InternalException(e.getLocalizedMessage());
		}

		long afterTime = Calendar.getInstance().getTimeInMillis();
		long elapsedTime = afterTime - beforeTime;

		ApiResponse body = response.getBody();

		ExternalResponse externalResponse = new ExternalResponse();
		externalResponse.setResponseCode(body.getResponseCode());
		externalResponse.setDescription(body.getDescription());
		externalResponse.setElapsedTime(elapsedTime);
		Result result = new Result();
		result.setRegisterCount(body.getResult().getItems().size());
		externalResponse.setResult(result);

		return externalResponse;
	}

	protected String encodeParam(String plainRut) throws InternalException {

		String encodedRut;
		DESKeySpec keySpec;
		try {
			keySpec = new DESKeySpec(getSecret().getBytes(StandardCharsets.UTF_8));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

			SecretKey generateSecret = keyFactory.generateSecret(keySpec);

			byte[] cleartext = plainRut.getBytes(StandardCharsets.UTF_8);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, generateSecret);

			encodedRut = Base64.getEncoder().encodeToString(cipher.doFinal(cleartext));

		} catch (Exception e) {
			throw new InternalException(e.getLocalizedMessage());
		}

		return encodedRut;
	}

	public String getSecret() {
		return secret;
	}

	public String getUrl() {
		return url;
	}
}
