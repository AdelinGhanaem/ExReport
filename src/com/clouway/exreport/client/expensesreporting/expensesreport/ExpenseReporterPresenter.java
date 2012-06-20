package com.clouway.exreport.client.expensesreporting.expensesreport;

import com.clouway.exreport.client.navigation.Presenter;

import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ExpenseReporterPresenter extends Presenter {

  public void fetchExpensesBetween(Date firstDate, Date secondDate);

  public void getAllExpensesDays(int year, int month);

  public void getMonthsOf(int year);

  public void getAllExpensesYears();

}