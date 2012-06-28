package com.clouway.exreport.server.authentication;

import com.clouway.exreport.server.accountcreation.AccountRepository;
import com.clouway.exreport.shared.entites.Account;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.entites.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationImplTest {

  @Mock
  AccountRepository repository;

  @Mock
  AuthenticatedUsersRepository authenticatedUsersRepository;

  UserAuthenticationImpl authenticationImpl;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    authenticationImpl = new UserAuthenticationImpl(repository, authenticatedUsersRepository);

  }

  @Test
  public void returnsAuthenticationTokenWhenUserIsAuthenticated() {

    String username = "username";

    String password = "password";

    when(repository.getAccount(username, password)).thenReturn(new Account(username, password));

    Token token = authenticationImpl.authenticate(new User(username, password));

    assertThat(token, is(notNullValue()));

    assertThat(token.getUser(), is(equalTo(username)));

    verify(repository).getAccount(username, password);

  }

  @Test
  public void addsUserToAuthenticatedUsersWhenUserIsAuthenticated() {

    String username = "username";

    String password = "password";

    String key = "key";

    User user = new User(username, password);

    Account account = new Account(key,username, password);

    when(repository.getAccount(username, password)).thenReturn(account);

   authenticationImpl.authenticate(user);

    verify(authenticatedUsersRepository).addToken(isA(Token.class), eq(account.getId()));



  }
  //TODO:continue testing here .... !

  @Test
  public void returnsNullAccountWhenUserIsNotRegistered() {
    String username = "user";

    String password = "password";

    when(repository.getAccount(username, password)).thenReturn(null);

    Token token = authenticationImpl.authenticate(new User(username, password));

    assertThat(token, is(nullValue()));

    verify(repository).getAccount(username, password);

  }


}
