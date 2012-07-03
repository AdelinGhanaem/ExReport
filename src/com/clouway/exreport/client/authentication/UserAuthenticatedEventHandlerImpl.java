package com.clouway.exreport.client.authentication;

import com.clouway.exreport.client.expensesreporting.dashboardview.DashboardPanel;
import com.clouway.exreport.client.navigation.InjectablePlaceController;
import com.clouway.exreport.client.navigation.places.DashboardPlace;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticatedEventHandlerImpl implements UserAuthenticatedEventHandler {


  private InjectablePlaceController controller;
  private SecurityTokenProvider tokenProvider;
  private final DashboardPanel panel;


  @Inject
  public UserAuthenticatedEventHandlerImpl(InjectablePlaceController controller,
                                           SecurityTokenProvider tokenProvider, DashboardPanel panel) {
    this.controller = controller;
    this.tokenProvider = tokenProvider;
    this.panel = panel;
  }


  @Override
  public void onUserAuthenticated(UserAuthenticatedEvent event) {

    tokenProvider.setToken(event.getToken());
    panel.setUsernameLabel(event.getToken().getUser());
    controller.goTo(new DashboardPlace());

  }
}
