package com.clouway.exreport.client.authentication;

import com.clouway.exreport.client.navigation.InjectablePlaceController;
import com.clouway.exreport.client.navigation.places.DashboardPlace;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.google.gwt.user.client.Cookies;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticatedEventHandlerImpl implements UserAuthenticatedEventHandler {


  private InjectablePlaceController controller;
  private SecurityTokenProvider tokenProvider;


  @Inject
  public UserAuthenticatedEventHandlerImpl(InjectablePlaceController controller, SecurityTokenProvider tokenProvider) {
    this.controller = controller;
    this.tokenProvider = tokenProvider;
  }


  @Override
  public void onUserAuthenticated(UserAuthenticatedEvent event) {

    Cookies.setCookie("username", event.getToken().getUser());

    tokenProvider.setToken(event.getToken());

    controller.goTo(new DashboardPlace());
  }
}
