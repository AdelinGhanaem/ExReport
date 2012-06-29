package com.clouway.exreport.client.expensesreporting.addingexpenses;

import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesView;
import com.clouway.exreport.client.expensesreporting.expensesreport.ExpenseReporterPresenter;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpenseAddedEventHandlerImpl implements ExpenseAddedEventHandler {

  private final AddExpensesView addExpense;
  private final ExpenseReporterPresenter presenter;

  @Inject
  public ExpenseAddedEventHandlerImpl(AddExpensesView addExpense, ExpenseReporterPresenter presenter) {
    this.addExpense = addExpense;
    this.presenter = presenter;
  }

  @Override
  public void onExpenseAdded(ExpenseAddedEvent event) {
    addExpense.enableAddButton();
    presenter.getAllExpensesYears();
  }
}
