package com.clouway.exreport.shared.Actions;

import com.clouway.exreport.shared.entites.Account;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreateAccountAction<AccountCreatedResponse> implements Action<Response> {

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