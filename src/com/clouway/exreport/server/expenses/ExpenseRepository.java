package com.clouway.exreport.server.expenses;

import com.clouway.exreport.shared.Expense;

import java.util.Date;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ExpenseRepository {
  Void saveExpense(Expense expense);

  List<Expense> getByDate(Date date);

  List<Expense> getByName(String expenseName);
}
