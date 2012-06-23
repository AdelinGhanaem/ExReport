package com.clouway.exreport.client;

import com.clouway.exreport.client.accountcreation.AccountCreatedEventHandler;
import com.clouway.exreport.client.authentication.UserAuthenticatedEventHandler;
import com.clouway.exreport.client.navigation.ApplicationActivityMapper;
import com.clouway.exreport.client.navigation.InjectableActivityManager;
import com.clouway.exreport.client.navigation.InjectablePlaceController;
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


  UserAuthenticatedEventHandler userAuthenticatedEventHandler();

  AccountCreatedEventHandler accountCreatedEventHandler();

  EventBus eventBus();

}
