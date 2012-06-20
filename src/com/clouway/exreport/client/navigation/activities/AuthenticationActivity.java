package com.clouway.exreport.client.navigation.activities;

import com.clouway.exreport.client.authentication.UserAuthenticationPresenter;
import com.clouway.exreport.client.authentication.view.UserAuthenticationView;
import com.clouway.exreport.client.navigation.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AuthenticationActivity extends AbstractActivity {

  @Inject
  private UserAuthenticationPresenter presenter;

  @Inject
  private UserAuthenticationView view;

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    view.setExpenseReporterPresenter(presenter);
    panel.setWidget(view.asWidget());
  }

}
