package com.clouway.exreport.server.expensesreporting;

import com.clouway.exreport.shared.entites.Day;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.entites.Month;
import com.clouway.exreport.shared.entites.Year;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class InMemoryExpensesRepository implements ExpensesRepository {

  private List<Expense> previouslySaved;

  private Map<Year, Map<Month, List<Day>>> map;

  public InMemoryExpensesRepository(List<Expense> previouslySaved) {

    this.previouslySaved = previouslySaved;
  }


  public InMemoryExpensesRepository() {
    previouslySaved = new ArrayList<Expense>();
    map = new HashMap<Year, Map<Month, List<Day>>>();

  }

  @Override
  public List<Expense> getByDate(Date date) {

    List<Expense> matchedExpenses = new ArrayList<Expense>();
//
    for (Expense expense : previouslySaved) {
      if (date.equals(expense.getDate())) {
        matchedExpenses.add(expense);
      }
    }
    return matchedExpenses;
  }

  @Override
  public List<Expense> getByName(String expenseName) {
    List<Expense> matchedExpenses = new ArrayList<Expense>();
//
    for (Expense expense : previouslySaved) {
      if (expenseName.equals(expense.getName())) {
        matchedExpenses.add(expense);
      }
    }
    return matchedExpenses;
  }

  @Override
  public List<Expense> getByDateBetween(Date firstDate, Date secondDate) {
    SortedSet<Expense> sortedExpense = new TreeSet<Expense>();
    for (Expense expense : previouslySaved) {
      if (firstDate.equals(expense.getDate()) || secondDate.equals(expense.getDate()) ||
              (firstDate.before(expense.getDate()) && secondDate.after(expense.getDate()))) {
        sortedExpense.add(expense);
      }
    }
    return new ArrayList<Expense>(sortedExpense);
  }

  @Override
  public void saveExpense(Expense expense) {
    previouslySaved.add(expense);
  }

  @Override
  public List<Month> getExpensesMonths(int yearNumber) {
    List<Month> months = null;

    for (Year year : map.keySet()) {
      if (year.getYear() == yearNumber) {
        months = new ArrayList<Month>(map.get(year).keySet());
        break;
      }
    }
    return months;
  }

  @Override
  public List<Day> getDeclaredDays(int yearNumber, int monthNumber) {
    List<Day> days = new ArrayList<Day>();

    for (Year year1 : map.keySet()) {
      if (year1.getYear() == yearNumber) {
        for (Month month : map.get(year1).keySet()) {
          if (month.getMonth() == monthNumber) {
            days = new ArrayList<Day>(map.get(year1).get(month));
          }
        }
      }
      break;
    }
    return days;
  }


  @Override
  public List<Year> getYears() {
    return new ArrayList<Year>();

  }


}
