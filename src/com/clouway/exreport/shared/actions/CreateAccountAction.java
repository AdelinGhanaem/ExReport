package com.clouway.exreport.shared.actions;

import com.clouway.exreport.shared.entites.Account;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

import java.io.Serializable;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreateAccountAction<AccountCreatedResponse> implements Action<Response> ,Serializable {

  private  Account account;

  public CreateAccountAction() {
  }
  public CreateAccountAction(Account account) {

    this.account = account;
  }

  public Account getAccount() {
    return account;
  }
}
