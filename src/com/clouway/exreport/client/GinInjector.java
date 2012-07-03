package com.clouway.exreport.client;

import com.clouway.exreport.client.accountregistration.AccountRegisteredEventHandler;
import com.clouway.exreport.client.authentication.UserAuthenticatedEventHandler;
import com.clouway.exreport.client.expensesreporting.addingexpenses.ExpenseAddedEventHandler;
import com.clouway.exreport.client.navigation.ApplicationActivityMapper;
import com.clouway.exreport.client.navigation.InjectableActivityManager;
import com.clouway.exreport.client.navigation.InjectablePlaceController;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@GinModules(ClientSideModule.class)
public interface GinInjector extends Ginjector {


  ApplicationActivityMapper activityMapper();

  InjectablePlaceController placeController();

  InjectableActivityManager activityManager();

  ExpenseAddedEventHandler expenseAddedEventHandler();


  UserAuthenticatedEventHandler userAuthenticatedEventHandler();

  AccountRegisteredEventHandler accountCreatedEventHandler();

  EventBus eventBus();

  SecurityTokenProvider securityToken();
}
