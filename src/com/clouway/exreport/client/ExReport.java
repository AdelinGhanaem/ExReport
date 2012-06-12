package com.clouway.exreport.client;

import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesViewImpl;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpensesReporterViewImpl;
import com.clouway.exreport.client.dashboard.CompositePanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class ExReport implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

//
//    SimpleLayoutPanel widgets = new SimpleLayoutPanel();
//
//    EventBus eventBus = new SimpleEventBus();
//
//    PlaceController placeController = new PlaceController(eventBus);
//
//    ActivityMapper mapper = new ApplicationActivityMapper();
//
//    ActivityManager manager = new ActivityManager(mapper, eventBus);
//
//    manager.setDisplay(widgets);
//
//    placeController.goTo(new Dashboard());


    ExpensesReporterViewImpl view = new ExpensesReporterViewImpl();

    AddExpensesViewImpl expensesView = new AddExpensesViewImpl();

    CompositePanel compositePanel = new CompositePanel();

    compositePanel.addWidget(view.asWidget());

    compositePanel.addWidget(expensesView.asWidget());


//
    RootLayoutPanel.get().add(compositePanel.asWidget());
//
// RootLayoutPanel.get().add(view.asWidget());


  }
}
