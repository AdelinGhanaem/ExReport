package com.clouway.exreport.server.accountcreation.actionhandlers;

import com.clouway.exreport.server.accountcreation.AccountCreator;
import com.clouway.exreport.shared.actions.RegisterAccountAction;
import com.clouway.exreport.shared.entites.Account;
import com.clouway.exreport.shared.reponses.RegisterAccountResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class RegisterAccountActionHandler implements ActionHandler<RegisterAccountAction, RegisterAccountResponse> {


  private final AccountCreator accountCreator;

  @Inject
  public RegisterAccountActionHandler(AccountCreator accountCreator) {

    this.accountCreator = accountCreator;
  }


  @Override
  public RegisterAccountResponse handle(RegisterAccountAction action) {

    List<String> error = new ArrayList<String>();

    Account account = accountCreator.create(action.getAccount(), error);

    return new RegisterAccountResponse(account, new ArrayList<String>(error));
  }

}
