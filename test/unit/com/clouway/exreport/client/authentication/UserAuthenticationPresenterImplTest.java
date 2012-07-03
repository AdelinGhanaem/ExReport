package com.clouway.exreport.client.authentication;

import com.clouway.exreport.client.authentication.view.UserAuthenticationView;
import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.clouway.exreport.shared.actions.LogoutAction;
import com.clouway.exreport.shared.actions.UserAuthenticationAction;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.entites.User;
import com.clouway.exreport.shared.reponses.LogoutResponse;
import com.clouway.exreport.shared.reponses.UserAuthenticationResponse;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.clouway.exreport.client.expensesreporting.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationPresenterImplTest {


  @Mock
  EventBus handlers;
  @Mock
  ActionDispatcherServiceAsync service;

  @Mock
  UserAuthenticationView view;

  UserAuthenticationPresenterImpl presenter;

  @Mock
  SecurityTokenProvider tokenProvider;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    handlers = spy(new SimpleEventBus());

    presenter = new UserAuthenticationPresenterImpl(view, service, handlers, tokenProvider);


  }

  @Test
  public void shouldAuthenticateUserAndFireUserAuthenticatedEvent() throws Exception {


    User user = new User("adio", "1234567");

    UserAuthenticationResponse response = new UserAuthenticationResponse(new Token(user.getUsername()));

    doOnSuccess(response).when(service).dispatch(any(UserAuthenticationAction.class), any(GotResponse.class));

    presenter.authenticate(user);

    verify(service).dispatch(any(UserAuthenticationAction.class), any(GotResponse.class));

    verify(handlers).fireEvent(any(UserAuthenticatedEvent.class));
  }

  //TODO:test on new registration button ....
  @Test
  public void goesToNewRegistrationPlaceOnNewRegistration() {

  }


  //TODO:continue with user validation ....

  @Test
  public void userWithEmptyUsernameIsNotAuthenticated() throws Exception {

    presenter.authenticate(new User("", "1234567"));

    verify(service, never()).dispatch(isA(UserAuthenticationAction.class), isA(GotResponse.class));

    verify(handlers, never()).fireEvent(any(UserAuthenticatedEvent.class));

    verify(view).emptyUsername();

  }


  @Test
  public void userWithEmptyPasswordIsNotAuthenticated() {

    presenter.authenticate(new User("Adelin", ""));

    verify(service, never()).dispatch(any(UserAuthenticationAction.class), any(GotResponse.class));

    verify(handlers, never()).fireEvent(any(UserAuthenticatedEvent.class));

    verify(view).emptyPassword();
  }


  @Test
  public void clearsTokenOnSuccessResponse() {

    Token token = new Token();

    LogoutResponse response = new LogoutResponse();

    when(tokenProvider.getToken()).thenReturn(token);

    doOnSuccess(response).when(service).dispatch(isA(LogoutAction.class), isA(GotResponse.class));

    presenter.onLogout(new LogoutEvent(token));

    verify(tokenProvider).getToken();

    verify(service).dispatch(isA(LogoutAction.class), isA(GotResponse.class));

    verify(tokenProvider).clearToken();


  }


}
