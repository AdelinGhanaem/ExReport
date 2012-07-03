package com.clouway.exreport.client.comunication;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ActionDispatcherServiceAsync {

  <T extends Response> void dispatch(Action<T> action, AsyncCallback<T> callback);
}
