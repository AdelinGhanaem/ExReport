package com.clouway.exreport.server.expensesreporting;

import com.clouway.exreport.shared.Expense;

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
}
