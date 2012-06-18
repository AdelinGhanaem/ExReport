package com.clouway.exreport.server.expensesreporting.actionhandlers;

import com.clouway.exreport.shared.Actions.FetchExpensesAction;
import com.clouway.exreport.shared.Reponses.FetchExpensesResponse;
import com.clouway.exreport.shared.entites.Expense;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchExpensesActionTest extends ExpensesActionHandlerTest {


  @Test
  public void fetchExpensesAndReturnsResponseContainsThem() throws ParseException {

    String expenseName = "food";

    double price = 12d;

    Date date = new SimpleDateFormat("yyyy-MM-DD").parse("2012-05-06");

    List<Expense> returnedExpense = new ArrayList<Expense>();

    returnedExpense.add(new Expense(expenseName, price, date));

    when(service.getExpensesBetween(date, date)).thenReturn(returnedExpense);

    FetchExpensesActionHandler handler = new FetchExpensesActionHandler(service);

    FetchExpensesResponse response = handler.handle(new FetchExpensesAction(date,date));

    assertThat(response, is(notNullValue()));

    assertThat(response.getExpenses(), is(notNullValue()));

    assertThat(response.getExpenses().get(0), is(notNullValue()));

    assertThat(response.getExpenses().get(0).getName(), is(expenseName));

    assertThat(response.getExpenses().get(0).getPrice(), is(price));

    assertThat(response.getExpenses().get(0).getDate(), is(date));

  }
}
