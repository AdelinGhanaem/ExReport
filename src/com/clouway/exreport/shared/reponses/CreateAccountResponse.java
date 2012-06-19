package com.clouway.exreport.shared.reponses;

import com.clouway.exreport.shared.entites.Account;
import com.evo.gad.shared.Response;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreateAccountResponse implements Response {


  private  Account account;

  private  ArrayList<String> error;

  public CreateAccountResponse() {
  }

  public CreateAccountResponse(Account account, ArrayList<String> error) {

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
