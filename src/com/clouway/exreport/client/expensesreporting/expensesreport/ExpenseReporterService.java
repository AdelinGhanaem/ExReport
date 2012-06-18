package com.clouway.exreport.client.expensesreporting.expensesreport;

import com.clouway.exreport.shared.entites.Day;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.entites.Month;
import com.clouway.exreport.shared.entites.Year;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@RemoteServiceRelativePath("exreportservice")

public interface ExpenseReporterService extends RemoteService {

    ArrayList<Expense> getExpensesFor(Date date);

   ArrayList<Expense> getExpensesBetween(Date firstDate, Date secondDate);

    ArrayList<Year> getYearsOfExpenses();

    ArrayList<Month> getMonthOf(int year);

    ArrayList<Day> getDaysOf(int year, int month);
}
