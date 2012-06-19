package com.clouway.exreport.client.navigation.activities;

import com.clouway.exreport.client.dashboard.DashboardPanel;
import com.clouway.exreport.client.expensesreporting.addingexpenses.AddExpensesPresenter;
import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesView;
import com.clouway.exreport.client.expensesreporting.expensesreport.ExpenseReporterPresenter;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpenseReporterView;
import com.clouway.exreport.client.navigation.AbstractActivity;
import com.evo.gad.dispatch.ActionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class DashboardActivity extends AbstractActivity {

  private ExpenseReporterPresenter expenseReporterPresenter;

  private AddExpensesPresenter addExpensesPresenter;

  private ExpenseReporterView expenseReporterView;

  private AddExpensesView addExpensesView;

  private DashboardPanel dashboardPanel;

  public DashboardActivity(ExpenseReporterPresenter expenseReporterPresenter, AddExpensesPresenter addExpensesPresenter,
                           ExpenseReporterView expenseReporterView, AddExpensesView addExpensesView, DashboardPanel panel) {
    this.expenseReporterPresenter = expenseReporterPresenter;
    this.addExpensesPresenter = addExpensesPresenter;
    this.expenseReporterView = expenseReporterView;
    this.addExpensesView = addExpensesView;
    this.dashboardPanel = panel;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    expenseReporterView.setExpenseReporterPresenter(expenseReporterPresenter);
    addExpensesView.setExpenseReporterPresenter(addExpensesPresenter);
    dashboardPanel.addWidget(expenseReporterView.asWidget());
    dashboardPanel.addWidget(addExpensesView.asWidget());
    panel.setWidget(dashboardPanel.asWidget());
  }
  
  ActionHandler
}
