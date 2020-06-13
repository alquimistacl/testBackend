package com.ionix.backend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ionix.backend.model.ExternalResponse;
import com.ionix.backend.model.Result;
import com.ionix.backend.model.User;
import com.ionix.backend.model.UserEntity;
import com.ionix.backend.service.ExternalApiService;
import com.ionix.backend.service.UserService;
import com.ionix.backend.util.DataUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(BackEndController.class)
public class BackendControllerTests {

	private static final String FAIL_MESSAGE = "Should not generate an exception";

	private static final String EMAIL = "prueba@destino.net";

	private static final String RUT = "1-9";

	@MockBean
	private UserService userService;

	@MockBean
	private ExternalApiService externalService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void shouldCreateUser() {

		User user = DataUtil.getUser();

		String userJson;
		try {
			userJson = mapper.writeValueAsString(user);
			MvcResult mvcResult = mockMvc.perform(post("/users").content(userJson).accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();

			MockHttpServletResponse response = mvcResult.getResponse();
			assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		} catch (Exception e) {
			fail(FAIL_MESSAGE);
		}

	}

	@Test
	public void shouldGetUsers() {
		try {
			List<UserEntity> userList = new ArrayList<UserEntity>();
			UserEntity user = DataUtil.getUser();
			userList.add(user);
			given(userService.getUsers()).willReturn(userList);

			MvcResult mvcResult = mockMvc.perform(get("/users/all").contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andReturn();
			MockHttpServletResponse response = mvcResult.getResponse();
			assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

			String body = response.getContentAsString();

			List<User> userListReceived = mapper.readValue(body, new TypeReference<List<User>>() {
			});

			assertThat(userListReceived.get(0).getEmail()).isEqualTo(user.getEmail());
		} catch (Exception e) {
			fail(FAIL_MESSAGE);
		}

	}

	@Test
	public void testName() {
		try {
			UserEntity user = DataUtil.getUser();
			given(userService.getUserByEmail(EMAIL)).willReturn(user);

			MvcResult mvcResult = mockMvc.perform(get("/users?email=" + EMAIL).contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andReturn();
			MockHttpServletResponse response = mvcResult.getResponse();
			assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

			String body = response.getContentAsString();

			User userReceived = mapper.readValue(body, User.class);
			assertThat(userReceived.getUsername()).isEqualTo(user.getUsername());

		} catch (Exception e) {
			fail(FAIL_MESSAGE);
		}
	}

	@Test
	public void shouldGetRutInfo() {
		try {
			ExternalResponse externalResponse = DataUtil.getExternalResponse();

			given(externalService.getExternalResponse(RUT)).willReturn(externalResponse);

			MvcResult mvcResult = mockMvc.perform(
					post("/rutinfo/" + RUT).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andReturn();

			MockHttpServletResponse response = mvcResult.getResponse();
			assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

			String body = response.getContentAsString();
			ExternalResponse externalResponseReceived = mapper.readValue(body, ExternalResponse.class);

			assertThat(externalResponseReceived.getDescription()).isEqualTo(externalResponse.getDescription());

		} catch (Exception e) {
			fail(FAIL_MESSAGE);
		}

	}




}
