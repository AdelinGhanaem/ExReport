package com.clouway.exreport.server.expenses;

import com.clouway.exreport.shared.Expense;

import java.util.Date;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpensesService {


  private final ExpenseRepository repository;

  public ExpensesService(ExpenseRepository repository) {

    this.repository = repository;

  }

  public Expense add(Expense expense) {
    if (expense != null) {
      repository.saveExpense(expense);
      return expense;
    }
    return null;
  }

  public List<Expense> getExpensesByDate(Date date) {
    return repository.getByDate(date);
  }


  public List<Expense> getExpensesByName(String expenseName) {
    return repository.getByName(expenseName);
  }

  public List<Expense> getExpensesBetween(Date firstDate, Date secondDate) {
    return repository.getByDateBetween(firstDate, secondDate);
  }
}
