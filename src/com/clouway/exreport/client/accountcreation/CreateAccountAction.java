package com.clouway.exreport.client.accountcreation;

import com.clouway.exreport.shared.Account;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreateAccountAction<AccountCreatedResponse> implements Action<Response> {

  private final Account account;

  public CreateAccountAction(Account account) {

    this.account = account;
  }

  public Account getAccount() {
    return account;
  }
}
