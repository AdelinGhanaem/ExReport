package com.clouway.exreport.server.expensesreporting;

import com.clouway.exreport.shared.entites.Account;
import com.clouway.exreport.shared.entites.Expense;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */

public class ExpensesServiceImplTest {


  @Mock
  ExpensesRepository repository;

  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  ExpensesServiceImpl expensesServiceImpl;


  @Before
  public void setUp() {

    initMocks(this);

    expensesServiceImpl = new ExpensesServiceImpl(repository);

  }


  @Test
  public void expensesAreSavedForSpecifiedAccount() {

    Account account = new Account();

    Expense expense = new Expense();

    expensesServiceImpl.add(expense);

    verify(repository).saveExpense(expense);

  }

  @Test
  public void returnsExpenseWhenExpenseIsAddedSuccessfully() throws ParseException {

    Expense expense = new Expense("expense", 12, dateFormat.parse("2012-06-12"));

    Expense addedExpense = expensesServiceImpl.add(expense);

    assertThat(addedExpense, is(notNullValue()));

    assertThat(addedExpense.getName(), is(equalTo(expense.getName())));

    assertThat(addedExpense.getPrice(), is(equalTo(expense.getPrice())));

    assertThat(addedExpense.getDate(), is(equalTo(expense.getDate())));

  }

  //TODO:I am not very sure about this one ..... !

  @Test
  public void nullExpensesAreNotSaved() {

    Expense nullExpense = null;

    Expense returned = expensesServiceImpl.add(nullExpense);

    verify(repository, never()).saveExpense(nullExpense);

    assertThat(returned, is(nullValue()));

  }

  @Test
  public void expensesAreReturnedByDate() throws ParseException {

    List<Expense> expenseList = new ArrayList<Expense>();

    String expenseName = "bla bla bla ";

    Date date = dateFormat.parse("2012-03-12");

    Double price = 12d;

    expenseList.add(new Expense(expenseName, price, date));


    when(repository.getExpenseByDate(date)).thenReturn(expenseList);

    List<Expense> returnedExpenses = expensesServiceImpl.getExpensesByDate(date);

    verify(repository).getExpenseByDate(date);

    assertThat(returnedExpenses.size(), is(equalTo(1)));

    assertThat(expenseList.get(0).getName(), is(equalTo(expenseName)));
    assertThat(expenseList.get(0).getDate(), is(equalTo(date)));
    assertThat(expenseList.get(0).getPrice(), is(equalTo(price)));
  }


  //TODO:change the method add(Expense expense,Date date)  to add(Expense expense); and put the Date into Expense.

  @Test
  public void expensesAreReturnedByName() throws ParseException {

    String expenseName = "Hubala Hubala";

    Date date = dateFormat.parse("2012-03-03");

    Double price = 100d;

    List<Expense> expenseList = new ArrayList<Expense>();

    expenseList.add(new Expense(expenseName, price, date));

    expenseList.add(new Expense("123", 100000d, date));

    when(repository.getExpenseByName(expenseName)).thenReturn(expenseList);

    List<Expense> returnedExpenses = expensesServiceImpl.getExpensesByName(expenseName);

    verify(repository).getExpenseByName(expenseName);

    assertThat(returnedExpenses.size(), is(equalTo(expenseList.size())));

    assertThat(returnedExpenses.containsAll(expenseList), is(true));

    assertThat(expenseList.get(0).getName(), is(equalTo(expenseName)));

    assertThat(expenseList.get(0).getDate(), is(equalTo(date)));

    assertThat(expenseList.get(0).getPrice(), is(equalTo(price)));
  }


  //TODO:what happens when the first date is after the seconds date ?
  //TODO what happens when the first date is in the future ?

  @Test
  public void expensesAreReturnedBetweenTwoDifferDates() throws ParseException {

    Date firstDate = dateFormat.parse("2012-09-05");

    Date secondDate = dateFormat.parse("2012-09-05");

    List<Expense> expenseList = new ArrayList<Expense>();

    expenseList.add(new Expense("first expense", 12d, firstDate));

    expenseList.add(new Expense("seconds expense", 12d, secondDate));

    when(repository.getExpensesBetween(firstDate, secondDate)).thenReturn(expenseList);

    List<Expense> returnedExpenses = expensesServiceImpl.getExpensesBetween(firstDate, secondDate);

    verify(repository).getExpensesBetween(firstDate, secondDate);

    assertThat(returnedExpenses.size(), is(equalTo(expenseList.size())));

    assertThat(returnedExpenses.containsAll(expenseList), is(true));

  }


  @Test
  public void returnsAllMonthsOfExpensesDeclaredYear() {

//    List<Month> months = new ArrayList<Month>();
//
//    int year = 2012;
//
//    months.add(new Month(year, 1));
//
//    months.add(new Month(year, 2));
//
//    when(repository.getExpensesMonths(year)).thenReturn(months);
//
//    List<Month> returnedExpenses = expensesServiceImpl.getMonths(year);
//
//    assertThat(returnedExpenses, is(notNullValue()));
//
//    assertThat(returnedExpenses.size(), is(2));
//
//    verify(repository).getExpensesMonths(year);
  }


  @Test
  public void returnsAllDaysOfExpensesDeclaredMonths() {

//    List<Day> days = new ArrayList<Day>();
//
//    int year = 2012;
//
//    int month = 3;
//
//    days.add(new Day(1, month, year));
//
//    days.add(new Day(2, month, year));
//
//    when(repository.getDeclaredDays(year, month)).thenReturn(days);
//
//    List<Day> returnedDays = expensesServiceImpl.getDays(year, month);
//
//    assertThat(returnedDays, is(notNullValue()));
//
//    assertThat(returnedDays.size(), is(2));

  }


  @Test
  public void returnsAllYearsOfExpensesDeclaration() {

//    List<Year> years = new ArrayList<Year>();
//
//    years.add(new Year(2012));
//
//    when(repository.getYears()).thenReturn(years);
//
//    List<Year> returnedYears = expensesServiceImpl.getDeclaredYears();
//
//    assertThat(returnedYears, is(notNullValue()));
//
//    assertThat(returnedYears.size(), is(1));
//
//    verify(repository).getYears();

  }

}
