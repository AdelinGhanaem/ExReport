package com.clouway.exreport.client.security;

import com.clouway.exreport.shared.SecurityTokenProvider;
import com.clouway.exreport.shared.entites.Token;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecurityActionFactoryImplTest {


  @Mock
  private SecurityTokenProvider provider;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
  }

  @Test
  public void returnsSecurityActionResponse() {
    Token token = new Token();

    SecurityActionFactoryImpl securityActionFactory = new SecurityActionFactoryImpl(provider);


    when(provider.getToken()).thenReturn(token);

    TestResponse response = new TestResponse();

    TestAction<TestResponse> testAction = new TestAction<TestResponse>();

    SecurityAction<TestResponse> returnedActionSecurity = securityActionFactory.createSecurityAction(testAction);

    assertThat(returnedActionSecurity, is(notNullValue()));
    assertThat(returnedActionSecurity.getAction(), is(notNullValue()));
    assertThat((TestAction<TestResponse>) returnedActionSecurity.getAction(), is(equalTo(testAction)));
    assertThat(returnedActionSecurity.getSecurityToke(), is(notNullValue()));
    assertThat(returnedActionSecurity.getSecurityToke(), is(equalTo(token)));


  }


  private class TestAction<TestResponse extends Response> implements Action<TestResponse> {

  }

  private class TestResponse implements Response {

  }


}
