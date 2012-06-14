package com.clouway.exreport.client.accountcreation;

import com.clouway.exreport.shared.Account;
import com.evo.gad.shared.Response;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountCreatedResponse implements Response {


  private  Account account;
  private final ArrayList<String> error;

  public AccountCreatedResponse(Account account, ArrayList<String> error) {

    this.account = account;
    this.error = error;
  }

  public Account getAccount() {


    return account;
  }

  public ArrayList<String> getErrors() {
    return error;
  }
}
