package com.clouway.exreport.client.expensesreporting.expensesreport.view;

import com.clouway.exreport.client.expensesreporting.expensesreport.ExpenseReporterPresenterImpl;
import com.clouway.exreport.client.navigation.View;
import com.clouway.exreport.shared.entites.Day;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.entites.Month;
import com.clouway.exreport.shared.entites.Year;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ExpenseReporterView extends View<ExpenseReporterPresenterImpl> {

    void updateExpenses(ArrayList<Expense> expenses);

    void notifyUserOfFutureDate();

    void notifyUserOfDateDiscrepancy();

    void showConnectionErrorMessage();

    Widget asWidget();

    void showExpensesYears(ArrayList<Year> yearList);

    void showMonthsOfExpenses(ArrayList<Month> months);

    void showDaysExpenses(ArrayList<Day> days);
}
