package com.clouway.exreport.shared.reponses;

import com.clouway.exreport.shared.entites.Account;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class RegisterAccountResponse implements Response ,IsSerializable {


  private  Account account;

  private  ArrayList<String> error;

  public RegisterAccountResponse() {
  }

  public RegisterAccountResponse(Account account, ArrayList<String> error) {

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
