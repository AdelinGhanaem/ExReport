package com.clouway.exreport.client.accountcreation;

import com.clouway.exreport.client.navigation.InjectablePlaceController;
import com.clouway.exreport.client.navigation.places.SuccessfulRegistrationsPlace;
import com.clouway.exreport.client.security.SecurityTokenProvider;
import com.clouway.exreport.shared.entites.Token;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountCreatedEventHandlerImpl implements AccountCreatedEventHandler {


  @Inject
  InjectablePlaceController placeController;

  @Inject
  SecurityTokenProvider provider;

  @Override
  public void onAccountCreated(AccountCreatedEvent event) {
    provider.setToken(new Token(event.getAccount().getEmail()));
    placeController.goTo(new SuccessfulRegistrationsPlace());
  }

}
