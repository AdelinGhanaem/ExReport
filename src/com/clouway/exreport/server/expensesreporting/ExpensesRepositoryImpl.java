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
public class ExpensesRepositoryImpl implements ExpensesRepository {
  @Override
  public void saveExpense(Expense expense) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public List<Expense> getByDate(Date date) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public List<Expense> getByName(String expenseName) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public List<Expense> getByDateBetween(Date firstDate, Date secondDate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public List<Month> getExpensesMonths(int year) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public List<Day> getDeclaredDays(int year, int month) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public List<Year> getYears() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
