package com.clouway.exreport.client;

import com.clouway.exreport.client.accountcreation.view.AccountCreatorViewImpl;
import com.clouway.exreport.client.dashboard.CompositePanel;
import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesViewImpl;
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

//    ExpensesReporterViewImpl view = new ExpensesReporterViewImpl();
//
    AddExpensesViewImpl expensesView = new AddExpensesViewImpl();

    CompositePanel compositePanel = new CompositePanel();

    AccountCreatorViewImpl accountCreatorView = new AccountCreatorViewImpl();

    RootLayoutPanel widgets = RootLayoutPanel.get();

    widgets.add(expensesView);

  }
}
