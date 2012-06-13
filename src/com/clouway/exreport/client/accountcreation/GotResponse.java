package com.clouway.exreport.client.accountcreation;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public abstract class GotResponse<AccountCreatedResponse> implements AsyncCallback<AccountCreatedResponse> {

  @Override
  public void onFailure(Throwable caught) {

  }

  @Override
  public void onSuccess(AccountCreatedResponse result) {
    gotResponse(result);
  }

  public abstract void gotResponse(AccountCreatedResponse result);

}
