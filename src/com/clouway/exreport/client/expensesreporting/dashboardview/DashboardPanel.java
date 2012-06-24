package com.clouway.exreport.client.expensesreporting.dashboardview;

import com.clouway.exreport.client.expensesreporting.addingexpenses.AddExpensesPresenter;
import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesViewImpl;
import com.clouway.exreport.client.expensesreporting.expensesreport.ExpenseReporterPresenter;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpensesReporterViewImpl;
import com.clouway.exreport.client.security.SecurityTokenProvider;
import com.clouway.exreport.shared.entites.Token;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class DashboardPanel extends Composite {

  interface CompositePanelUiBinder extends UiBinder<HTMLPanel, DashboardPanel> {

  }

  private static CompositePanelUiBinder ourUiBinder = GWT.create(CompositePanelUiBinder.class);


//  @UiField
//  HTMLPanel centerPanel;

  @UiField
  ExpensesReporterViewImpl expensesPanel;

//  @UiField
//  AddExpensesViewImpl addExpensesPanel;

  @Inject
  SecurityTokenProvider provider;

  HTMLPanel rootPanel;

  public DashboardPanel() {
    rootPanel = ourUiBinder.createAndBindUi(this);
    initWidget(rootPanel);
  }

  public void setExpensesPanelPresenter(ExpenseReporterPresenter expensesPanelPresenter) {
    expensesPanel.setPresenter(expensesPanelPresenter);
  }

  public void setAddingExpensesPanelPresenter(AddExpensesPresenter addExpensesPresenter) {
//    addExpensesPanel.setPresenter(addExpensesPresenter);
  }


}