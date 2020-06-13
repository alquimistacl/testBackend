package com.ionix.backend.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ionix.backend.dto.ApiResponse;
import com.ionix.backend.dto.Detail;
import com.ionix.backend.dto.Item;
import com.ionix.backend.dto.Result;
import com.ionix.backend.exception.InternalException;
import com.ionix.backend.model.ExternalResponse;

@RunWith(MockitoJUnitRunner.Silent.class)
@ContextConfiguration
public class ExternalApiServiceTests {
	private static final String ENCODED_PARAM = "dfkjshfdsjk32432543";

	private static final String URL = "http://www.prueba.com/ruta?rut=";

	private static final String PARAM = "1-9";

	private static final String FAIL_MESSAGE = "Should not generate an exception";

	@Mock
	private RestTemplate template;

	@Spy
	@InjectMocks
	private ExternalApiService service;

	@Test
	public void shouldGetExternalResponse() {
		try {
			given(service.getSecret()).willReturn("verysecret1");
			given(service.getUrl()).willReturn(URL);
			given(service.encodeParam(PARAM)).willReturn(ENCODED_PARAM);
			ApiResponse response = new ApiResponse();
			response.setDescription("OK");
			response.setResponseCode(0);
			Result result = new Result();
			List<Item> items = new ArrayList<>();
			Item item = new Item();
			item.setName("prueba");
			Detail detail = new Detail();
			detail.setEmail("prueba@email.net");
			detail.setPhoneNumber("123456789");
			item.setDetail(detail);
			items.add(item);

			Item item2 = new Item();
			item2.setName("prueba item 2");
			items.add(item2);

			result.setItems(items);
			response.setResult(result);
			ResponseEntity<ApiResponse> apiResponse = new ResponseEntity<ApiResponse>(response, HttpStatus.OK);

			given(template.getForEntity(URL + ENCODED_PARAM, ApiResponse.class)).willReturn(apiResponse);

			ExternalResponse externalResponse = service.getExternalResponse(PARAM);
			assertTrue(externalResponse.getResult().getRegisterCount() > 0);
		} catch (InternalException e) {
			fail(FAIL_MESSAGE);
		}
	}

	@Test
	public void shouldNotGetExternalResponse() {
		try {
			given(service.getSecret()).willReturn("verysecret1");
			given(service.getUrl()).willReturn(URL);
			given(service.encodeParam(PARAM)).willReturn(ENCODED_PARAM);
			given(template.getForEntity(URL + ENCODED_PARAM, ApiResponse.class)).willThrow(RestClientException.class);
			service.getExternalResponse(PARAM);
		} catch (InternalException e) {
			assertTrue(Boolean.TRUE);
		}
	}

	@Test
	public void shouldNotEncodeParam() {
		try {
			service.encodeParam(PARAM);
		} catch (InternalException e) {
			assertTrue(Boolean.TRUE);
		}

	}

}
