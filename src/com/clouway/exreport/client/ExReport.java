package com.clouway.exreport.client;

import com.clouway.exreport.client.expensesdashboard.view.ExpensesReporterDashboardViewImpl;
import com.clouway.exreport.client.mainview.CompositePanel;
import com.clouway.exreport.shared.Expense;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import java.util.ArrayList;

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


    ExpensesReporterDashboardViewImpl dashboardView = new ExpensesReporterDashboardViewImpl();

    dashboardView.renderTodaysExpense(new ArrayList<Expense>() {{
      add(new Expense("food", 123d));
      add(new Expense("diskoteka", 100d));
      add(new Expense("Drugi gluposti", 500d));
    }});

    CompositePanel compositePanel = new CompositePanel();

    compositePanel.addWidget(dashboardView.asWidget());
//
    RootLayoutPanel.get().add(compositePanel.asWidget());
//
// RootLayoutPanel.get().add(dashboardView.asWidget());


  }
}
