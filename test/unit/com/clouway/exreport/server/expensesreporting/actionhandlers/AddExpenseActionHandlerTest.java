package com.clouway.exreport.server.expensesreporting.actionhandlers;

import com.clouway.exreport.server.expensesreporting.ExpensesService;
import com.clouway.exreport.shared.actions.AddExpenseAction;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.reponses.AddExpenseResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AddExpenseActionHandlerTest {

  @Mock
  ExpensesService expensesService;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void shouldAddExpenseAndReturnResponseContainingIt() {

    Expense expense = new Expense();

    Date date = new Date();

    AddExpenseActionHandler addExpenseActionHandler = new AddExpenseActionHandler(expensesService);

    when(expensesService.add(expense)).thenReturn(expense);

    AddExpenseResponse returnedResponse = addExpenseActionHandler.handle(new AddExpenseAction(expense));

    assertThat(returnedResponse, is(notNullValue()));

    assertThat(returnedResponse.getExpense(), is(notNullValue()));

    verify(expensesService).add(expense);

  }

  //TODO:Continue testing with error scenarios .... !

}
