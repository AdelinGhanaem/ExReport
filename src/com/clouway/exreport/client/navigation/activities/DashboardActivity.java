package com.clouway.exreport.client.navigation.activities;

import com.clouway.exreport.client.security.SecurityTokenProvider;
import com.clouway.exreport.client.expensesreporting.dashboardview.DashboardPanel;
import com.clouway.exreport.client.expensesreporting.addingexpenses.AddExpensesPresenter;
import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesView;
import com.clouway.exreport.client.expensesreporting.expensesreport.ExpenseReporterPresenterImpl;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpenseReporterView;
import com.clouway.exreport.client.navigation.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class DashboardActivity extends AbstractActivity {

  @Inject
  private ExpenseReporterPresenterImpl expenseReporterPresenter;

  @Inject
  private AddExpensesPresenter addExpensesPresenter;

  @Inject
  private ExpenseReporterView expenseReporterView;

  @Inject
  private AddExpensesView addExpensesView;

  @Inject
  private DashboardPanel dashboardPanel;

  @Inject
  private SecurityTokenProvider provider;

  @Inject
  public DashboardActivity(ExpenseReporterPresenterImpl expenseReporterPresenter, AddExpensesPresenter addExpensesPresenter,

                           ExpenseReporterView expenseReporterView, AddExpensesView addExpensesView, DashboardPanel panel) {
    this.expenseReporterPresenter = expenseReporterPresenter;
    this.addExpensesPresenter = addExpensesPresenter;
    this.expenseReporterView = expenseReporterView;
    this.addExpensesView = addExpensesView;
    this.dashboardPanel = panel;

  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    expenseReporterView.setPresenter(expenseReporterPresenter);

    addExpensesView.setPresenter(addExpensesPresenter);

    dashboardPanel.addWidget(expenseReporterView.asWidget());

    dashboardPanel.addWidget(addExpensesView.asWidget());

    dashboardPanel.setToken(provider.getToken());

    panel.setWidget(dashboardPanel.asWidget());
  }

}
