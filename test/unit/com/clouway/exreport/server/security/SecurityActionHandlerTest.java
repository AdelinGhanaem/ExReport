package com.clouway.exreport.server.security;

import com.clouway.exreport.client.security.SecurityAction;
import com.clouway.exreport.server.authentication.AuthenticatedUsersRepository;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.evo.gad.dispatch.ActionDispatcher;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecurityActionHandlerTest {


  private class TestAction implements Action<TestResponse> {
  }

  private class TestResponse implements Response {

  }

  @Mock
  private ActionDispatcher dispatcher;

  @Mock
  private AuthenticatedUsersRepository repository;

  private SecurityActionHandler<TestResponse, SecurityResponse<TestResponse>, TestAction, SecurityAction<TestAction>> handler;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    handler = new SecurityActionHandler<TestResponse, SecurityResponse<TestResponse>, TestAction, SecurityAction<TestAction>>(dispatcher, repository);

  }

  @Test
  public void callsActionDispatcherToHandlerTheCarriedAction() {

    TestResponse response = new TestResponse();

    String username = "adelin";

    Token token = new Token(username);

    TestAction testAction = new TestAction();

    SecurityAction<TestAction> action = new SecurityAction<TestAction>(testAction, token);

    when(dispatcher.dispatch(isA(TestAction.class))).thenReturn(response);

    when(repository.isAuthorized(token)).thenReturn(true);

    SecurityResponse<TestResponse> returnedResponse = handler.handle(action);

    verify(dispatcher).dispatch(testAction);

    assertThat(returnedResponse, is(notNullValue()));

    assertThat(returnedResponse.getResponse(), is(notNullValue()));

    assertThat(returnedResponse.getResponse(), is(response));

  }


  @Test
  public void nullResponseIsReturnedWhenTokenIsNull() {

    TestAction testAction = new TestAction();

    Token nullToke = null;

    SecurityAction<TestAction> action = new SecurityAction<TestAction>(testAction, nullToke);

    SecurityResponse<TestResponse> testResponse = handler.handle(action);

    verify(dispatcher, never()).dispatch(testAction);

    assertThat(testResponse.getResponse(), is(nullValue()));

  }


  @Test
  public void responseIsReturnedWhenUserIsAuthorized() {

    TestResponse response = new TestResponse();

    String username = "username";

    Token token = new Token(username);

    TestAction testAction = new TestAction();

    SecurityAction<TestAction> action = new SecurityAction<TestAction>(testAction, token);

    when(dispatcher.dispatch(isA(TestAction.class))).thenReturn(response);

    when(repository.isAuthorized(token)).thenReturn(true);

    SecurityResponse<TestResponse> returnedResponse = handler.handle(action);

    verify(repository).isAuthorized(token);

    verify(dispatcher).dispatch(testAction);

    assertThat(returnedResponse, is(notNullValue()));

    assertThat(returnedResponse.getResponse(), is(notNullValue()));

    assertThat(returnedResponse.getResponse(), is(response));
  }


  @Test
  public void nullResponseIsReturnedWhenUserIsNotAuthorized() {
    TestResponse response = new TestResponse();

    String username = "username";


    Token token = new Token(username);

    TestAction testAction = new TestAction();

    SecurityAction<TestAction> action = new SecurityAction<TestAction>(testAction, token);

    when(dispatcher.dispatch(isA(TestAction.class))).thenReturn(response);

    when(repository.isAuthorized(token)).thenReturn(false);

    SecurityResponse<TestResponse> returnedResponse = handler.handle(action);

    verify(repository).isAuthorized(token);

    verify(dispatcher, never()).dispatch(testAction);

    assertThat(returnedResponse, is(notNullValue()));

    assertThat(returnedResponse.getResponse(), is(nullValue()));
  }




}
