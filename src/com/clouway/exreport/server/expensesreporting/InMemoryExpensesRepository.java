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
    map.put(new Year(2011), null);
    map.put(new Year(2012), null);
    map.put(new Year(2013), null);

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
  public List<Month> getExpensesMonths(final int yearNumber) {
    List<Month> months = new ArrayList<Month>();
    months.add(new Month(yearNumber, 2));
    months.add(new Month(yearNumber, 3));
    months.add(new Month(yearNumber, 4));
    months.add(new Month(yearNumber, 5));
    months.add(new Month(yearNumber, 6));
    months.add(new Month(yearNumber, 7));
    months.add(new Month(yearNumber, 8));
    return months;
  }

  @Override
  public List<Day> getDeclaredDays(final int yearNumber, final int monthNumber) {
    List<Day> days = new ArrayList<Day>() {{
      add(new Day(1, monthNumber, yearNumber));
      add(new Day(2, monthNumber, yearNumber));
      add(new Day(3, monthNumber, yearNumber));
      add(new Day(4, monthNumber, yearNumber));
      add(new Day(5, monthNumber, yearNumber));
    }};

    return days;
  }


  @Override
  public List<Year> getYears() {
    return new ArrayList<Year>() {{
      add(new Year(2011));
      add(new Year(2012));
      add(new Year(2013));
    }};
  }


}
