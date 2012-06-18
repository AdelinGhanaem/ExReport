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
public interface ExpensesRepository {

  void saveExpense(Expense expense);

  List<Expense> getByDate(Date date);

  List<Expense> getByName(String expenseName);

  List<Expense> getByDateBetween(Date firstDate, Date secondDate);

  List<Month> getExpensesMonths(int year);

  List<Day> getDeclaredDays(int year, int month);

  List<Year> getYears();
}
