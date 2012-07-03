package com.clouway.exreport.client.accountregistration;

import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.security.SecurityAction;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class MySample {

  class UnsecuredActionDispatch implements ActionDispatcherServiceAsync {


    @Override
    public <T extends Response> void dispatch(Action<T> action, AsyncCallback<T> callback) {
      //To change body of implemented methods use File | Settings | File Templates.
    }
  }

  class SecuredActionDispatch implements ActionDispatcherServiceAsync {

    private final UnsecuredActionDispatch actionDispatch;
    private final SecurityTokenProvider tokenProvider;

    public SecuredActionDispatch(UnsecuredActionDispatch actionDispatch, SecurityTokenProvider tokenProvider) {

      this.actionDispatch = actionDispatch;
      this.tokenProvider = tokenProvider;
    }

    @Override
    public <T extends Response> void dispatch(Action<T> action, final AsyncCallback<T> callback) {
      Token token = tokenProvider.getToken();

      SecurityAction<T> aa = new SecurityAction<T>(action,token);
      actionDispatch.dispatch(aa, new AsyncCallback<SecurityResponse>() {
        @Override
        public void onFailure(Throwable caught) {

        }

        @Override
        public void onSuccess(SecurityResponse result) {
          callback.onSuccess((T) result.getResponse());
        }
      });
    }
  }
}
