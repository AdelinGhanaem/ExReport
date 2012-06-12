package com.clouway.exreport.client;

import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesViewImpl;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpensesReporterViewImpl;
import com.clouway.exreport.client.dashboard.CompositePanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class ExReport implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        ExpensesReporterViewImpl view = new ExpensesReporterViewImpl();

        AddExpensesViewImpl expensesView = new AddExpensesViewImpl();

        CompositePanel compositePanel = new CompositePanel();

        RootLayoutPanel widgets = RootLayoutPanel.get();

        widgets.add(compositePanel);
    }
}
