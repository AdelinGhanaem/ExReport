package com.clouway.exreport.client.expensesdashboard.expensesreview.view;

import com.clouway.exreport.shared.Day;
import com.clouway.exreport.shared.Expense;
import com.clouway.exreport.shared.Month;
import com.clouway.exreport.shared.Year;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ExpenseReporterDashBoardView {

    void updateExpenses(ArrayList<Expense> expenses);

    void notifyUserOfFutureDate();

    void notifyUserOfDateDiscrepancy();

    void showConnectionErrorMessage();

    Widget asWidget();

    void showExpensesYears(ArrayList<Year> yearList);

    void showMonthsOfExpenses(ArrayList<Month> months);

    void showDaysExpenses(ArrayList<Day> days);
}
