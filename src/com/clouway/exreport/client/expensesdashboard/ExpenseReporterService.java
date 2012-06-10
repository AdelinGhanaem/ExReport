package com.clouway.exreport.client.expensesdashboard;

import com.clouway.exreport.shared.Day;
import com.clouway.exreport.shared.Expense;
import com.clouway.exreport.shared.Month;
import com.clouway.exreport.shared.Year;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@RemoteServiceRelativePath("exreportservice")
public interface ExpenseReporterService extends RemoteService {

    List<Expense> getExpensesFor(Date date);

    List<Expense> getExpensesBetween(Date firstDate, Date secondDate);

    ArrayList<Year> getYearsOfExpenses();

    ArrayList<Month> getMonthOf(int year);

    ArrayList<Day> getDaysOf(int year, int month);
}
