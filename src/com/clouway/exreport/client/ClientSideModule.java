package com.clouway.exreport.client;

import com.clouway.exreport.client.accountcreation.AccountCreatedEventHandler;
import com.clouway.exreport.client.accountcreation.AccountCreatedEventHandlerImpl;
import com.clouway.exreport.client.accountcreation.AccountCreatorPresenter;
import com.clouway.exreport.client.accountcreation.AccountCreatorPresenterImpl;
import com.clouway.exreport.client.accountcreation.view.AccountCreatorView;
import com.clouway.exreport.client.accountcreation.view.AccountCreatorViewImpl;
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
import com.clouway.exreport.client.expensesreporting.addingexpenses.ExpenseAddedEventHandler;
import com.clouway.exreport.client.expensesreporting.addingexpenses.ExpenseAddedEventHandlerImpl;
import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesView;
import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesViewImpl;
import com.clouway.exreport.client.expensesreporting.expensesreport.ExpenseReporterPresenter;
import com.clouway.exreport.client.expensesreporting.expensesreport.ExpenseReporterPresenterImpl;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpenseReporterView;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpensesReporterViewImpl;
import com.clouway.exreport.client.navigation.SecureActivityMapperProvider;
import com.clouway.exreport.client.security.SecurityActionFactory;
import com.clouway.exreport.client.security.SecurityActionFactoryImpl;
import com.clouway.exreport.client.security.SecurityTokenProviderImpl;
import com.clouway.exreport.shared.AccountValidationErrorMessages;
import com.clouway.exreport.shared.AccountValidationErrorMessagesImpl;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
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
    bind(UserAuthenticationView.class).to(UserAuthenticationViewImpl.class).in(Singleton.class);

    bind(UserAuthenticationPresenter.class).to(UserAuthenticationPresenterImpl.class);

    //communication


    //adding expenses 
    bind(AddExpensesView.class).to(AddExpensesViewImpl.class).in(Singleton.class);

    bind(AddExpensesPresenter.class).to(AddExpensesPresenterImpl.class);

    //reporting expenses 
    bind(ExpenseReporterView.class).to(ExpensesReporterViewImpl.class).in(Singleton.class);

    bind(ExpenseReporterPresenter.class).to(ExpenseReporterPresenterImpl.class);

    //navigation 
    bind(ActivityMapper.class).toProvider(SecureActivityMapperProvider.class);

//    bind(SecurityTokenProviderImpl.class)

    //EventHandlers
    bind(UserAuthenticatedEventHandler.class).to(UserAuthenticatedEventHandlerImpl.class);

    bind(SecurityTokenProvider.class).to(SecurityTokenProviderImpl.class).in(Singleton.class);

    bind(SecurityActionFactory.class).to(SecurityActionFactoryImpl.class);

    bind(AccountCreatedEventHandler.class).to(AccountCreatedEventHandlerImpl.class);

    bind(ExpenseAddedEventHandler.class).to(ExpenseAddedEventHandlerImpl.class);


  }


  @Provides
  public ActionDispatcherServiceAsync provideDispatcherServiceAsync() {
    return GWT.create(ActionDispatcherService.class);
  }


}
