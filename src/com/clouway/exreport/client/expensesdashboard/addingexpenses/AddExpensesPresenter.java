package com.clouway.exreport.client.expensesdashboard.addingexpenses;

import com.clouway.exreport.shared.Expense;

import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AddExpensesPresenter {
  public void addExpense(Expense expense, Date date);
}
