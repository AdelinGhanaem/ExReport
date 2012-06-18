package com.clouway.exreport.server;

import com.evo.gad.dispatch.ActionHandler;
import com.evo.gad.dispatch.ActionHandlerRepository;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class TestHandlersRepository implements ActionHandlerRepository{
  
  

  @Override
  public ActionHandler getActionHandler(Class<? extends Action> aClass) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
