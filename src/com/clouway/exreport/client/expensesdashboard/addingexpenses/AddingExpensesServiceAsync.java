package com.clouway.exreport.client.expensesdashboard.addingexpenses;

import com.clouway.exreport.shared.Expense;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AddingExpensesServiceAsync {
  void addExpense(Expense expense, Date expenseDate, AsyncCallback asyncCallback);
}
