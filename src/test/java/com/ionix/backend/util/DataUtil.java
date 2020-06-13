package com.ionix.backend.util;

import com.ionix.backend.model.ExternalResponse;
import com.ionix.backend.model.Result;
import com.ionix.backend.model.UserEntity;

public class DataUtil {

	public static UserEntity getUser() {
		UserEntity user = new UserEntity();

		user.setEmail("prueba@destino.net");
		user.setName("nombre prueba");
		user.setPhone("123456789");
		user.setUsername("usuario1");
		user.setId(1l);
		return user;
	}
	
	public static ExternalResponse getExternalResponse() {
		ExternalResponse externalResponse = new ExternalResponse();
		externalResponse.setDescription("OK");
		externalResponse.setResponseCode(0);
		externalResponse.setElapsedTime(100l);
		Result result = new Result();
		result.setRegisterCount(3);
		externalResponse.setResult(result);
		return externalResponse;
	}
}
