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
public class ExpensesServiceImpl implements ExpensesService {


  private final ExpensesRepository repository;

  public ExpensesServiceImpl(ExpensesRepository repository) {
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

  public List<Month> getMonths(int year) {

    return repository.getExpensesMonths(year);
  }

  public List<Day> getDays(int year, int month) {
    return repository.getDeclaredDays(year, month);
  }

  @Override
  public List<Year> getDeclaredYears() {
    return repository.getYears();
  }

}
