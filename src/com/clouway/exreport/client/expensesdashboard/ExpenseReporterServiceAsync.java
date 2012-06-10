package com.clouway.exreport.client.expensesdashboard;

import com.clouway.exreport.shared.Expense;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ExpenseReporterServiceAsync {

  void getExpensesFor(Date date, AsyncCallback<List<Expense>> asyncCallback);

  void getExpensesBetween(Date firstDate, Date secondDate, AsyncCallback<List<Expense>> any);
}
