package com.clouway.exreport.client.navigation.activities;


import com.clouway.exreport.client.accountcreation.AccountCreatorPresenterImpl;
import com.clouway.exreport.client.accountcreation.view.AccountCreatorView;
import com.clouway.exreport.client.navigation.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class NewRegistrationActivity extends AbstractActivity {

  private final AccountCreatorPresenterImpl presenter;

  private final AccountCreatorView view;

  @Inject
  public NewRegistrationActivity(AccountCreatorPresenterImpl presenter, AccountCreatorView view) {
    this.presenter = presenter;
    this.view = view;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    view.setPresenter(presenter);
    panel.setWidget(view.asWidget());
  }
}
