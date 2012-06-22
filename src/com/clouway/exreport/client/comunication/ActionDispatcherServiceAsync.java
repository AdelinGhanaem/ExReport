package com.clouway.exreport.client.comunication;

import com.clouway.exreport.client.security.SecurityAction;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ActionDispatcherServiceAsync {

  public<T extends Response> void dispatch(Action<? extends Response> action, AsyncCallback<T> callback);


  <E extends Response, R extends SecurityResponse<E>, A extends Action<E>> void dispatchSecurityAction(SecurityAction<A> action, AsyncCallback<R> async);
}
