package com.clouway.exreport.client;

import com.clouway.exreport.client.accountregistration.AccountRegisteredEvent;
import com.clouway.exreport.client.authentication.LogoutEvent;
import com.clouway.exreport.client.authentication.UserAuthenticatedEvent;
import com.clouway.exreport.client.expensesreporting.addingexpenses.ExpenseAddedEvent;
import com.clouway.exreport.client.navigation.ApplicationPlaceHistoryMapper;
import com.clouway.exreport.client.navigation.places.DashboardPlace;
import com.clouway.exreport.client.navigation.places.NewRegistrationPlace;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class ExReport implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    SimpleLayoutPanel panel = new SimpleLayoutPanel();

    GinInjector injector = GWT.create(GinInjector.class);

    PlaceController controller = injector.placeController();

    ActivityManager manager = injector.activityManager();

    manager.setDisplay(panel);

    RootLayoutPanel.get().add(panel);

    EventBus eventBus = injector.eventBus();

    ApplicationPlaceHistoryMapper historyMapper = GWT.create(ApplicationPlaceHistoryMapper.class);

    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);

    historyHandler.register(controller, eventBus, new NewRegistrationPlace());

    eventBus.addHandler(UserAuthenticatedEvent.TYPE, injector.userAuthenticatedEventHandler());

    eventBus.addHandler(AccountRegisteredEvent.TYPE, injector.accountCreatedEventHandler());

    eventBus.addHandler(ExpenseAddedEvent.TYPE, injector.expenseAddedEventHandler());

    eventBus.addHandler(LogoutEvent.TYPE,injector.logoutEventHandler()) ;

    SecurityTokenProvider provider = injector.securityToken();

    controller.goTo(new DashboardPlace());

  }

}
