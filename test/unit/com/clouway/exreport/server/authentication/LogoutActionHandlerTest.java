package com.clouway.exreport.server.authentication;

import com.clouway.exreport.shared.actions.LogoutAction;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.reponses.LogoutResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class LogoutActionHandlerTest {


  @Mock
  AuthenticatedUsersRepository repository;

  LogoutActionHandler handler;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    handler = new LogoutActionHandler(repository);
  }


  @Test
  public void deletesTokenFromAuthenticatedUsersRepository() {

    Token token = new Token();

    LogoutResponse response = handler.handle(new LogoutAction(token));

    assertThat(response, is(notNullValue()));

    verify(repository).deleteToken(token);

  }

  @Test
  public void doNotDeleteTokenFromRepositoryWhenTokenIsNull() {

    Token token = null;

    LogoutResponse response = handler.handle(new LogoutAction(token));

    assertThat(response, is(notNullValue()));

    verify(repository, never()).deleteToken(token);
  }


}
