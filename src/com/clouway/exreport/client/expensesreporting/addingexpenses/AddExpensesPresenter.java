package com.clouway.exreport.client.expensesreporting.addingexpenses;

import com.clouway.exreport.client.navigation.Presenter;
import com.clouway.exreport.shared.entites.Expense;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AddExpensesPresenter extends Presenter {
  public void addExpense(Expense expense);
}
