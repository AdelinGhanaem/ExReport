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
  public List<Expense> getExpenseByDate(Date date) {

    List<Expense> matchedExpenses = new ArrayList<Expense>();
    matchedExpenses.add(new Expense("food", 12d, date));
    matchedExpenses.add(new Expense("dress", 12d, date));
    matchedExpenses.add(new Expense("disko", 12d, date));
    matchedExpenses.add(new Expense("fuel", 12d, date));
    matchedExpenses.add(new Expense("othe bullshits ... !", 12d, date));
    matchedExpenses.add(new Expense("Fix PC", 12d, date));
    matchedExpenses.add(new Expense("others ", 12d, date));

    return matchedExpenses;
  }

  @Override
  public List<Expense> getExpenseByName(String expenseName) {
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
  public List<Expense> getExpensesBetween(Date date, Date secondDate) {
    List<Expense> matchedExpenses = new ArrayList<Expense>();
    matchedExpenses.add(new Expense("food", 12d, date));
    matchedExpenses.add(new Expense("dress", 12d, date));
    matchedExpenses.add(new Expense("disko", 12d, date));
    matchedExpenses.add(new Expense("fuel", 12d, date));
    matchedExpenses.add(new Expense("othe bullshits ... !", 12d, date));
    matchedExpenses.add(new Expense("Fix PC", 12d, date));
    matchedExpenses.add(new Expense("others ", 12d, date));

    return matchedExpenses;
  }

  @Override
  public void saveExpense(Expense expense) {
    previouslySaved.add(expense);
  }





}
