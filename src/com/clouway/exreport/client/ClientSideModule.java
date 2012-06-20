package com.clouway.exreport.client;

import com.clouway.exreport.client.accountcreation.AccountCreatorPresenter;
import com.clouway.exreport.client.accountcreation.AccountCreatorPresenterImpl;
import com.clouway.exreport.client.accountcreation.AccountValidationErrorMessages;
import com.clouway.exreport.client.accountcreation.AccountValidationErrorMessagesImpl;
import com.clouway.exreport.client.accountcreation.view.AccountCreatorView;
import com.clouway.exreport.client.accountcreation.view.AccountCreatorViewImpl;
import com.clouway.exreport.client.authentication.SecurityTokenProvider;
import com.clouway.exreport.client.authentication.SecurityTokenProviderImpl;
import com.clouway.exreport.client.authentication.UserAuthenticatedEvent;
import com.clouway.exreport.client.authentication.UserAuthenticatedEventHandler;
import com.clouway.exreport.client.authentication.UserAuthenticatedEventHandlerImpl;
import com.clouway.exreport.client.authentication.UserAuthenticationPresenter;
import com.clouway.exreport.client.authentication.UserAuthenticationPresenterImpl;
import com.clouway.exreport.client.authentication.view.UserAuthenticationView;
import com.clouway.exreport.client.authentication.view.UserAuthenticationViewImpl;
import com.clouway.exreport.client.comunication.ActionDispatcherService;
import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.expensesreporting.addingexpenses.AddExpensesPresenter;
import com.clouway.exreport.client.expensesreporting.addingexpenses.AddExpensesPresenterImpl;
import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesView;
import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesViewImpl;
import com.clouway.exreport.client.expensesreporting.expensesreport.ExpenseReporterPresenter;
import com.clouway.exreport.client.expensesreporting.expensesreport.ExpenseReporterPresenterImpl;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpenseReporterView;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpensesReporterViewImpl;
import com.clouway.exreport.client.navigation.ApplicationActivityMapper;
import com.clouway.exreport.shared.entites.Token;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.user.client.Cookies;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ClientSideModule extends AbstractGinModule {
  @Override
  protected void configure() {

    bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

    //account creation
    bind(AccountCreatorView.class).to(AccountCreatorViewImpl.class).in(Singleton.class);

    bind(AccountCreatorPresenter.class).to(AccountCreatorPresenterImpl.class);

    bind(AccountValidationErrorMessages.class).to(AccountValidationErrorMessagesImpl.class);

    //User authentication
    bind(UserAuthenticationView.class).to(UserAuthenticationViewImpl.class);

    bind(UserAuthenticationPresenter.class).to(UserAuthenticationPresenterImpl.class).in(Singleton.class);

    //communication


    //adding expenses 
    bind(AddExpensesView.class).to(AddExpensesViewImpl.class);

    bind(AddExpensesPresenter.class).to(AddExpensesPresenterImpl.class);

    //reporting expenses 
    bind(ExpenseReporterView.class).to(ExpensesReporterViewImpl.class);

    bind(ExpenseReporterPresenter.class).to(ExpenseReporterPresenterImpl.class);

    //navigation 
    bind(ActivityMapper.class).to(ApplicationActivityMapper.class);

//    bind(SecurityTokenProvider.class)

    //EventHandlers
    bind(UserAuthenticatedEventHandler.class).to(UserAuthenticatedEventHandlerImpl.class);

    bind(SecurityTokenProvider.class).to(SecurityTokenProviderImpl.class).in(Singleton.class);

  }


  @Provides
  public ActionDispatcherServiceAsync dispatcherServiceAsync() {
    return GWT.create(ActionDispatcherService.class);
  }

}
