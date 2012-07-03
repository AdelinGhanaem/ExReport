package com.clouway.exreport.client.navigation.activities;

import com.clouway.exreport.client.accountregistration.view.SuccessfulRegistration;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SuccessfulRegistrationsActivity extends AbstractActivity {

  @Inject
  SuccessfulRegistration registration;

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    panel.setWidget(registration.asWidget());

  }
}
