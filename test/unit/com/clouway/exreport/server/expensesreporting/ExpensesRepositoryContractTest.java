package com.clouway.exreport.server.expensesreporting;

import com.clouway.exreport.shared.entites.Expense;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpensesRepositoryContractTest {

  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
  private List<Expense> previouslySaved = new ArrayList<Expense>();
  private ExpensesRepository expensesRepository;

  @Before
  public void setUp() throws ParseException {
    expensesRepository = new InMemoryExpensesRepository(previouslySaved);
  }

  @Test
  public void expensesAreReturnedByDate() throws ParseException {

//    String date = "2012-10-03";
//
//    Date expenseDate = dateFormat.parse(date);
//
//    Expense expense = new Expense("food", 100d, expenseDate);
//
//    previouslySaved.add(expense);
//
//    previouslySaved.add(new Expense());
//
//    List<Expense> returnedExpenses = expensesRepository.getExpenseByDate(expenseDate);
//
//    Expense returnedExpense = returnedExpenses.get(0);
//
//    assertThat(returnedExpense, is(notNullValue()));
//
//    assertThat(returnedExpenses.size(), is(1));
//
//    assertThat(returnedExpense.getDate(), is(equalTo(expense.getDate())));
//
//    assertThat(returnedExpense.getName(), is(equalTo(expense.getName())));
//
//    assertThat(returnedExpense.getPrice(), is(equalTo(expense.getPrice())));
  }


  @Test
  public void expensesAreReturnedByName() throws ParseException {


    String name = "expense name";

    Expense expense = new Expense(name, 100d, dateFormat.parse("2012-12-03"));

    previouslySaved.add(expense);

    previouslySaved.add(new Expense());

    List<Expense> expenseList = expensesRepository.getExpenseByName(name);

    Expense returnedExpense = expenseList.get(0);

    assertThat(returnedExpense, is(notNullValue()));

    assertThat(expenseList.size(), is(1));

    assertThat(returnedExpense.getDate(), is(equalTo(expense.getDate())));

    assertThat(returnedExpense.getName(), is(equalTo(expense.getName())));

    assertThat(returnedExpense.getPrice(), is(equalTo(expense.getPrice())));

  }


  @Test
  public void expensesAreReturnedBetweenTwoDates() throws ParseException {

    String firstDate = "2010-03-01";

    String secondsDate = "2011-03-01";

    Expense firstExpense = new Expense("food", 1000d, dateFormat.parse(firstDate));

    Expense secondExpense = new Expense("food", 1000d, dateFormat.parse(secondsDate));

    Expense outOfRangeExpense = new Expense("food", 2000d, dateFormat.parse("2009-05-09"));

    previouslySaved.add(firstExpense);

    previouslySaved.add(outOfRangeExpense);

    previouslySaved.add(secondExpense);

    List<Expense> expenseList = expensesRepository.getExpensesBetween(dateFormat.parse(firstDate), dateFormat.parse(secondsDate));

    assertThat(expenseList.size(), is(2));

    assertThat(expenseList.get(0).getDate(), is(dateFormat.parse(firstDate)));

    assertThat(expenseList.get(1).getDate(), is(dateFormat.parse(secondsDate)));

  }

  @Test
  public void returnsYearsOfDeclaredExpenses() {

  }

}

