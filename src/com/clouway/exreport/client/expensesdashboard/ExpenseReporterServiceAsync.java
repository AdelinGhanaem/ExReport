package com.clouway.exreport.client.expensesdashboard;

import com.clouway.exreport.shared.Day;
import com.clouway.exreport.shared.Expense;
import com.clouway.exreport.shared.Month;
import com.clouway.exreport.shared.Year;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ExpenseReporterServiceAsync {

    void getExpensesFor(Date date, AsyncCallback<List<Expense>> asyncCallback);

    void getExpensesBetween(Date firstDate, Date secondDate, AsyncCallback<List<Expense>> any);

    void getYearsOfExpenses(AsyncCallback<ArrayList<Year>> callback);

    void getMonthOf(int year, AsyncCallback<ArrayList<Month>> any);

    void getDaysOf(int year, int month, AsyncCallback<ArrayList<Day>> any);

}
