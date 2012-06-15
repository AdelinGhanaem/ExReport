package com.clouway.exreport.shared;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpenseComparisonTest {
  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");


  @Test
  public void expensesAreComparedAlphabeticallyFirst() throws ParseException {
    Expense expenseOne = new Expense("A", 12d, dateFormat.parse("2011-01-01"));
    Expense expenseTwo = new Expense("B", 13d, dateFormat.parse("2010-01-01"));
    assertThat(expenseOne.compareTo(expenseTwo) < 0, is(true));
  }


  @Test
  public void expensesAreSortedByDateSecond() throws ParseException {

    Expense expenseOne = new Expense("A", 12d, dateFormat.parse("2010-01-01"));

    Expense expenseTwo = new Expense("A", 13d, dateFormat.parse("2011-01-01"));

    assertThat(expenseOne.compareTo(expenseTwo) < 0, is(true));
  }


  @Test
  public void expensesAreSortByDateThirdly() throws ParseException {

    Expense expenseTwo = new Expense("A", 13d, dateFormat.parse("2010-01-01"));

    Expense expenseOne = new Expense("A", 12d, dateFormat.parse("2010-01-01"));

    int result = expenseOne.compareTo(expenseTwo);

    assertThat(result < 0, is(true));
  }



}
