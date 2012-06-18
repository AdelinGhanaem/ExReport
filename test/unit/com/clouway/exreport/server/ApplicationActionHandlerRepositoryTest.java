package com.clouway.exreport.server;

import com.clouway.exreport.server.accountcreation.actionhandlers.CreateAccountActionHandler;
import com.clouway.exreport.shared.actions.CreateAccountAction;
import com.evo.gad.dispatch.ActionHandler;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ApplicationActionHandlerRepositoryTest {

  @Test
  public void retunrsActionHadelr() throws Exception {
    Map<Class<? extends Action>, Class<? extends ActionHandler<? extends Action, ? extends Response>>> handlers =
            new TreeMap<Class<? extends Action>, Class<? extends ActionHandler<? extends Action, ? extends Response>>>();

    handlers.put(CreateAccountAction.class,CreateAccountActionHandler.class);
  }
}
