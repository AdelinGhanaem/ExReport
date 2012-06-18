package com.clouway.exreport.server.expensesreporting;

import com.clouway.exreport.shared.entites.Day;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.entites.Month;
import com.clouway.exreport.shared.entites.Year;

import java.util.Date;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ExpensesService {

  public Expense add(Expense expense);

  public List<Expense> getExpensesByDate(Date date);

  public List<Expense> getExpensesByName(String expenseName);

  public List<Expense> getExpensesBetween(Date firstDate, Date secondDate);

  public List<Month> getMonths(int year);

  public List<Day> getDays(int year, int month);

  List<Year> getDeclaredYears();
}
