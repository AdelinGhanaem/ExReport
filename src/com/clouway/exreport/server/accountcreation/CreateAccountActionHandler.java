package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.client.accountcreation.AccountCreatedResponse;
import com.clouway.exreport.client.accountcreation.CreateAccountAction;
import com.clouway.exreport.shared.Account;
import com.evo.gad.dispatch.ActionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreateAccountActionHandler implements ActionHandler<CreateAccountAction, AccountCreatedResponse> {


  private final AccountCreator accountCreator;

  public CreateAccountActionHandler(AccountCreator accountCreator) {

    this.accountCreator = accountCreator;
  }

  @Override
  public AccountCreatedResponse handle(CreateAccountAction action) {

    List<String> error = new ArrayList<String>();

    Account account = accountCreator.create(action.getAccount(), error);

    return new AccountCreatedResponse(account, (ArrayList<String>) error);
  }
}
