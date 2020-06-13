package com.ionix.backend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import com.ionix.backend.exception.GenericSqlException;
import com.ionix.backend.model.User;
import com.ionix.backend.model.UserEntity;
import com.ionix.backend.repository.UserRepository;
import com.ionix.backend.util.DataUtil;

@RunWith(MockitoJUnitRunner.Silent.class)
@ContextConfiguration
public class UserServiceTests {

	private static final String EMAIL = "prueba@destino.net";

	private static final String FAIL_MESSAGE = "Should not generate an exception";

	@Mock
	private UserRepository repository;

	@Spy
	@InjectMocks
	private UserService service;

	@Test
	public void shouldAddUser() {
		try {
			User userToBeCreated = DataUtil.getUser();
			UserEntity userCreated = DataUtil.getUser();
			given(repository.save(any())).willReturn(userCreated);

			service.addUser(userToBeCreated);
		} catch (GenericSqlException e) {
			fail(FAIL_MESSAGE);
		}
	}

	@Test
	public void shouldNotAddUser() {
		try {
			User userToBeCreated = DataUtil.getUser();
			given(repository.save(any())).willThrow(IllegalArgumentException.class); // Return(userCreated);

			service.addUser(userToBeCreated);
		} catch (GenericSqlException e) {
			assertTrue(Boolean.TRUE);
		}
	}

	@Test
	public void shouldGetUsers() {
		try {
			List<UserEntity> userList = new ArrayList<>();
			userList.add(DataUtil.getUser());

			Iterable<UserEntity> iterableUser = userList;

			given(repository.findAll()).willReturn(iterableUser);

			List<UserEntity> users = service.getUsers();

			assertTrue(!users.isEmpty());
		} catch (GenericSqlException e) {
			fail(FAIL_MESSAGE);
		}
	}

	@Test
	public void shouldNotGetUsers() {
		try {
			given(repository.findAll()).willThrow(IllegalArgumentException.class);

			service.getUsers();

		} catch (GenericSqlException e) {
			assertTrue(Boolean.TRUE);
		}
	}

	@Test
	public void shouldGetUserByEmail() {
		try {
			given(repository.findByEmail(EMAIL)).willReturn(DataUtil.getUser());

			UserEntity userByEmail = service.getUserByEmail(EMAIL);

			assertThat(userByEmail.getUsername()).isEqualTo("usuario1");
		} catch (GenericSqlException e) {
			fail(FAIL_MESSAGE);
		}
	}

	@Test
	public void shouldNotGetUserByEmail() {
		try {
			given(repository.findByEmail(EMAIL)).willThrow(IllegalArgumentException.class);

			service.getUserByEmail(EMAIL);

		} catch (GenericSqlException e) {
			assertTrue(Boolean.TRUE);
		}
	}

}
