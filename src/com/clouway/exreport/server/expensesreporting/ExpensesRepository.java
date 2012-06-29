package com.clouway.exreport.server.expensesreporting;

import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.entites.Year;

import java.util.Date;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ExpensesRepository {

  void saveExpense(Expense expense);

  List<Expense> getExpenseByDate(Date date);

  List<Expense> getExpenseByName(String expenseName);

  List<Expense> getExpensesBetween(Date firstDate, Date secondDate);
  
  List<Year> getYears();
  
}


