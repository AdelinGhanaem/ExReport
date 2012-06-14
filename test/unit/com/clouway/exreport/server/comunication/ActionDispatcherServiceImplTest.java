package com.clouway.exreport.server.comunication;

import com.evo.gad.dispatch.ActionHandler;
import com.evo.gad.dispatch.ActionHandlerRepository;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ActionDispatcherServiceImplTest {


  @Mock
  ActionHandlerRepository repository;

  private ActionDispatcherServiceImpl actionDispatcherService;


  @Before
  public void setUp() {

    initMocks(this);

    actionDispatcherService = new ActionDispatcherServiceImpl(repository);

  }

  @Test
  public void shouldDispatchActionAndReturnResponse() {

    MyAction myAction = new MyAction();

    when(repository.getActionHandler(myAction.getClass())).thenReturn(new MyActionHandler());

    Response response = actionDispatcherService.dispatch(myAction);

    assertThat(response, is(notNullValue()));

    Class<MyResponse> aClass = MyResponse.class;

    assertTrue(aClass.equals(response.getClass()));
  }


  private class MyAction implements Action<MyResponse> {

  }

  private class MyResponse implements Response {

  }

  private class MyActionHandler implements ActionHandler {
    @Override
    public Response handle(Action action) {
      return new MyResponse();
    }
  }

}
