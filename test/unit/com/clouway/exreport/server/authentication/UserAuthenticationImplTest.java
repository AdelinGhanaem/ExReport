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
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationImplTest {

  @Mock
  AccountRepository repository;



  @Before
  public void setUp() throws Exception {
    initMocks(this);
  }

  @Test
  public void returnsAuthenticationTokenWhenUserIsAuthenticated() {

    String username = "username";

    String password = "password";

    UserAuthenticationImpl authenticationImpl = new UserAuthenticationImpl(repository);

    when(repository.getAccount(username, password)).thenReturn(new Account(username));

    Token token = authenticationImpl.authenticate(new User(username, password));

    assertThat(token, is(notNullValue()));

    assertThat(token.getUser(), is(equalTo(username)));

    verify(repository).getAccount(username, password);

  }

                //TODO:continue testing here .... !
  @Test
  public void returnsNullWhenUserIsNotRegistered() {

  }

}
