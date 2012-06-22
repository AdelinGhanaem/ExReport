package com.clouway.exreport.server.authentication;

import com.clouway.exreport.shared.actions.UserAuthenticationAction;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.entites.User;
import com.clouway.exreport.shared.reponses.UserAuthenticationResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationActionHandlerTest {


  @Mock
  UserAuthentication authentication;

  @Mock
  AuthenticatedUsersRepository authenticatedUsersRepository;

  UserAuthenticationActionHandler handler;


  @Before
  public void setUp() throws Exception {

    initMocks(this);

    handler = new UserAuthenticationActionHandler(authentication, authenticatedUsersRepository);

  }

  @Test
  public void authenticatesUserAndReturnsResponseContainsTokenCreated() throws Exception {

    String username = "username";

    String password = "password";

    User user = new User(username, password);

    when(authentication.authenticate(user)).thenReturn(new Token(username));

    UserAuthenticationResponse response = handler.handle(new UserAuthenticationAction(user));

    assertThat(response, is(notNullValue()));

    assertThat(response.getToken(), is(notNullValue()));

    assertThat(response.getToken().getUser(), is(notNullValue()));

    assertThat(response.getToken().getUser(), is(username));

    verify(authentication).authenticate(user);

  }

  @Test
  public void persistsNewTokenForUserWhenUserIsAuthorized() {
    String username = "username";

    String password = "password";

    User user = new User(username, password);

    Token token = new Token(username);

    when(authentication.authenticate(user)).thenReturn(token);

    handler.handle(new UserAuthenticationAction(user));

    verify(authenticatedUsersRepository).persist(token);
  }


  @Test
  public void tokenIsNotPersistedIfUserIsNoAuthorized() {

    User user = new User();

    when(authentication.authenticate(user)).thenReturn(null);

    handler.handle(new UserAuthenticationAction(user));

    verify(authenticatedUsersRepository, never()).persist(null);

  }


}
