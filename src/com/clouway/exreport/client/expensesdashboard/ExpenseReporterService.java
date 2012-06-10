package com.clouway.exreport.client.expensesdashboard;

import com.clouway.exreport.shared.Expense;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.Date;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@RemoteServiceRelativePath("ExpenseReporterService")
public interface ExpenseReporterService extends RemoteService {

  List<Expense> getExpensesFor(Date date);

  List<Expense> getExpensesBetween(Date firstDate, Date secondDate);
}
