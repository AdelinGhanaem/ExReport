package com.clouway.exreport.shared.actions;

import com.clouway.exreport.shared.entites.Account;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class RegisterAccountAction<AccountCreatedResponse extends Response> implements Action<AccountCreatedResponse> ,IsSerializable {

  private  Account account;

  public RegisterAccountAction() {

  }
  public RegisterAccountAction(Account account) {

    this.account = account;
  }

  public Account getAccount() {
    return account;
  }
}
