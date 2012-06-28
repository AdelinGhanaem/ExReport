package com.clouway.exreport.server.comunication;

import com.clouway.exreport.client.security.SecurityAction;
import com.clouway.exreport.server.authentication.AuthenticatedUsersRepository;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.evo.gad.dispatch.ActionHandlerRepository;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ActionDispatcherServiceImplTest {


  @Mock
  ActionHandlerRepository actionHandlerRepository;

  @Mock
  AuthenticatedUsersRepository authenticatedUsersRepository;

  @Mock
  SecurityTokenProvider tokenProvider;
  private ActionDispatcherServiceImpl dispatcher;


  @Before
  public void setUp() {
    initMocks(this);
    dispatcher = new ActionDispatcherServiceImpl(actionHandlerRepository, authenticatedUsersRepository, tokenProvider);
  }

  @Test
  public void shouldDispatchActionAndReturnResponse() {

    MyAction myAction = new MyAction();

    when(actionHandlerRepository.getActionHandler(myAction.getClass())).thenReturn(new MyActionHandler());

    Response response = dispatcher.dispatch(myAction);

    assertThat(response, is(notNullValue()));

    Class<MyResponse> aClass = MyResponse.class;

    assertTrue(aClass.equals(response.getClass()));


  }


  

  private class MyActionHandler implements ActionHandler {
    @Override
    public Response handle(Action action) {
      return new MyResponse();
    }
  }

  @Test
  public void dispatchesCarriedActionWhenTokenIsAuthorized() {

    String username = "adelin";

    Token token = new Token(username);

    TestAction testAction = new TestAction();

    when(authenticatedUsersRepository.getTokenKey(token)).thenReturn("key");

    when(actionHandlerRepository.getActionHandler(TestAction.class)).thenReturn(new TestActionHandler());

    SecurityAction<TestAction> action = new SecurityAction<TestAction>(testAction, token);

    SecurityResponse<TestResponse> returnedResponse = dispatcher.dispatchSecurityAction(action);

    assertThat(returnedResponse, is(notNullValue()));

    assertThat(returnedResponse.getResponse(), is(notNullValue()));

    assertThat((Class<TestResponse>) returnedResponse.getResponse().getClass(), is(equalTo(TestResponse.class)));

  }


  @Test
  public void nullResponseIsReturnedWhenTokenIsNull() {

    TestAction testAction = new TestAction();

    Token nullToke = null;

    SecurityAction<TestAction> action = new SecurityAction<TestAction>(testAction, nullToke);

    SecurityResponse<TestResponse> testResponse = dispatcher.dispatchSecurityAction(action);

    assertThat(testResponse.getResponse(), is(nullValue()));

  }


  @Test
  public void nullResponseIsReturnedWhenUserIsNotAuthorized() {

    TestResponse response = new TestResponse();

    String username = "username";

    Token token = new Token(username);

    TestAction testAction = new TestAction();

    SecurityAction<TestAction> action = new SecurityAction<TestAction>(testAction, token);

    when(authenticatedUsersRepository.getTokenKey(token)).thenReturn(null);

    SecurityResponse<TestResponse> returnedResponse = dispatcher.dispatchSecurityAction(action);

    verify(authenticatedUsersRepository).getTokenKey(token);

    assertThat(returnedResponse, is(notNullValue()));

    assertThat(returnedResponse.getResponse(), is(nullValue()));
  }


  private class TestAction implements Action<TestResponse> {
  }

  private class TestResponse implements Response {

  }

  private class MyAction implements Action<MyResponse> {

  }

  private class MyResponse implements Response {

  }


  private class TestActionHandler implements ActionHandler<TestAction, TestResponse> {
    @Override
    public TestResponse handle(TestAction action) {
      return new TestResponse();
    }
  }

}
