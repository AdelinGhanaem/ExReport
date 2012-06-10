package com.clouway.exreport.client.expensesdashboard.view;

import com.clouway.exreport.shared.Expense;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ExpenseReporterDashBoardView {

  void renderTodaysExpense(List<Expense> expenses);

  void notifyUserOfFutureDate();

  void notifyUserOfDateDiscrepancy();

  void showConnectionErrorMessage();

  Widget asWidget();
}
