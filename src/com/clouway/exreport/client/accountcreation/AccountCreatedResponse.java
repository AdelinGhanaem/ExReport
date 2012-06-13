package com.clouway.exreport.client.accountcreation;

import com.clouway.exreport.shared.Account;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountCreatedResponse implements Response {


  private  Account account;

  public AccountCreatedResponse(Account account) {

    this.account = account;
  }

  public Account getAccount() {
    return account;
  }
}
