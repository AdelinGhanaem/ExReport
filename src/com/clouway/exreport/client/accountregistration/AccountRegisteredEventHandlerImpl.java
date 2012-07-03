package com.clouway.exreport.client.accountregistration;

import com.clouway.exreport.client.navigation.InjectablePlaceController;
import com.clouway.exreport.client.navigation.places.SuccessfulRegistrationsPlace;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.clouway.exreport.shared.entites.Token;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountRegisteredEventHandlerImpl implements AccountRegisteredEventHandler {


  @Inject
  InjectablePlaceController placeController;

  @Inject
  SecurityTokenProvider provider;

  @Override
  public void onAccountRegistered(AccountRegisteredEvent event) {
    provider.setToken(new Token(event.getAccount().getEmail()));
    placeController.goTo(new SuccessfulRegistrationsPlace());
  }

}
