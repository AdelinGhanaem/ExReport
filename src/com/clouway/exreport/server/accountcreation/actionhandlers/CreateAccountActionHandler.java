package com.clouway.exreport.server.accountcreation.actionhandlers;

import com.clouway.exreport.server.accountcreation.AccountCreator;
import com.clouway.exreport.shared.actions.CreateAccountAction;
import com.clouway.exreport.shared.entites.Account;
import com.clouway.exreport.shared.reponses.CreateAccountResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreateAccountActionHandler implements ActionHandler<CreateAccountAction, CreateAccountResponse> {


  private final AccountCreator accountCreator;

  @Inject
  public CreateAccountActionHandler(AccountCreator accountCreator) {

    this.accountCreator = accountCreator;
  }


  @Override
  public CreateAccountResponse handle(CreateAccountAction action) {

    List<String> error = new ArrayList<String>();

    Account account = accountCreator.create(action.getAccount(), error);

    return new CreateAccountResponse(account, new ArrayList<String>(error));
  }

}
