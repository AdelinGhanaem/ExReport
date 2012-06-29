package com.clouway.exreport.client.expensesreporting.addingexpenses;

import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesView;
import com.clouway.exreport.client.expensesreporting.expensesreport.ExpenseReporterPresenter;
import com.clouway.exreport.shared.entites.Expense;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpenseAddedEventHandlerImplTest {

  @Mock
  private AddExpensesView view;

  @Mock
  private ExpenseReporterPresenter presenter;

  private ExpenseAddedEventHandlerImpl expenseAddedEventHandler;


  @Before
  public void setUp() throws Exception {
    initMocks(this);
    expenseAddedEventHandler = new ExpenseAddedEventHandlerImpl(view, presenter);
  }

  //TODO:make it update the table .... !

  @Test
  public void enablesAddButtonOnExpenseAdded() {
    expenseAddedEventHandler.onExpenseAdded(new ExpenseAddedEvent(new Expense()));
    verify(view).enableAddButton();
  }

  @Test
  public void shouldUpdateYearsWhenOnExpenseAdded() {
    expenseAddedEventHandler.onExpenseAdded(new ExpenseAddedEvent(new Expense()));
    verify(presenter).getAllExpensesYears();
  }
}
