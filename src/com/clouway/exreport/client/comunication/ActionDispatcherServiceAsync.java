package com.clouway.exreport.client.comunication;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ActionDispatcherServiceAsync {

  public<T extends Response> void dispatch(Action<? extends Response> action, AsyncCallback<T> callback);


}
