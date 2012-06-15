package com.clouway.exreport.server.expensesreporting;

import com.clouway.exreport.shared.Expense;

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
}
