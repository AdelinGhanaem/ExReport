package com.clouway.exreport.server.expenses;

import com.clouway.exreport.shared.Account;
import com.clouway.exreport.shared.Expense;
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

public class ExpensesServiceTest {


  @Mock
  ExpenseRepository repository;

  ExpensesService expensesService;


  @Before
  public void setUp() {

    initMocks(this);

    expensesService = new ExpensesService(repository);

  }


  @Test
  public void expensesAreSavedForSpecifiedAccount() {
    Account account = new Account();

    Expense expense = new Expense();

    expensesService.add(expense);

    verify(repository).saveExpense(expense);

  }

  @Test
  public void returnsExpenseWhenExpenseIsAddedSuccessfully() {

    Expense expense = new Expense("expense", 12);

    Expense addedExpense = expensesService.add(expense);

    assertThat(addedExpense, is(notNullValue()));

    assertThat(addedExpense.getName(), is(equalTo(expense.getName())));

    assertThat(addedExpense.getPrice(), is(equalTo(expense.getPrice())));

  }

  //TODO:I am not very sure about this one ..... !
  @Test
  public void nullExpensesAreNotSaved() {

    Expense nullExpense = null;

    Expense returned = expensesService.add(nullExpense);

    verify(repository, never()).saveExpense(nullExpense);

    assertThat(returned, is(nullValue()));

  }

  @Test
  public void expensesAreReturnedByDate() throws ParseException {

    List<Expense> expenseList = new ArrayList<Expense>();

    expenseList.add(new Expense("bla bla bla ", 12d));

    expenseList.add(new Expense("gr gr gr ", 12d));

    Date date = new SimpleDateFormat("yyyy/MM/dd").parse("2012/09/05");

    when(repository.getByDate(date)).thenReturn(expenseList);

    List<Expense> returnedExpenses = expensesService.getExpensesByDate(date);

    verify(repository).getByDate(date);

    assertThat(returnedExpenses.size(), is(equalTo(expenseList.size())));

    assertThat(returnedExpenses.containsAll(expenseList), is(true));

  }


  //TODO:change the method add(Expense expense,Date date)  to add(Expense expense); and put the Date into Expense.

  @Test
  public void expensesAreReturnedByName() {

    String expenseName = "Hubala Hubala";

    List<Expense> expenseList = new ArrayList<Expense>();

    expenseList.add(new Expense(expenseName, 120000d));

    expenseList.add(new Expense(expenseName, 100000d));

    when(repository.getByName(expenseName)).thenReturn(expenseList);

    List<Expense> returnedExpenses = expensesService.getExpensesByName(expenseName);

    verify(repository).getByName(expenseName);

    assertThat(returnedExpenses.size(), is(equalTo(expenseList.size())));

    assertThat(returnedExpenses.containsAll(expenseList), is(true));

  }


  @Test
  public void expensesAreReturnedBetweenTwoDifferDates() throws ParseException {
    List<Expense> expenseList = new ArrayList<Expense>();

    expenseList.add(new Expense("first expense", 12d));

    expenseList.add(new Expense("seconds expense", 12d));

    Date firstDate = new SimpleDateFormat("yyyy/MM/dd").parse("2012/09/05");
    Date secondDate = new SimpleDateFormat("yyyy/MM/dd").parse("2012/09/05");

    when(repository.getByDateBetween(firstDate,secondDate)).thenReturn(expenseList);

    List<Expense> returnedExpenses = expensesService.getExpensesBetween(firstDate,secondDate);

    verify(repository).getByDateBetween(firstDate,secondDate);

    assertThat(returnedExpenses.size(), is(equalTo(expenseList.size())));

    assertThat(returnedExpenses.containsAll(expenseList), is(true));

  }

}
