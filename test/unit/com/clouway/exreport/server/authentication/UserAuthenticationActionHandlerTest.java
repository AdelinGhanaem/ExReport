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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationActionHandlerTest {


  @Mock
  UserAuthentication authentication;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
  }

  @Test
  public void authenticatesUserAndReturnsResponseContainsTokenCreated() throws Exception {

    String username = "username";
    String password = "password";

    UserAuthenticationActionHandler handler = new UserAuthenticationActionHandler(authentication);

    User user = new User(username, password);

    when(authentication.authenticate(user)).thenReturn(new Token(username));

    UserAuthenticationResponse response = handler.handle(new UserAuthenticationAction(user));

    assertThat(response, is(notNullValue()));

    assertThat(response.getToken(), is(notNullValue()));

    assertThat(response.getToken().getUser(), is(notNullValue()));

    assertThat(response.getToken().getUser(), is(username));

    verify(authentication).authenticate(user);


  }


}
