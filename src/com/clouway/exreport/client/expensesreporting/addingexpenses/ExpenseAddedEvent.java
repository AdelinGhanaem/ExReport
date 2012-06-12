package com.clouway.exreport.client.expensesreporting.addingexpenses;

import com.clouway.exreport.shared.Expense;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpenseAddedEvent extends GwtEvent<ExpenseAddedEventHandler> {

  public static Type<ExpenseAddedEventHandler> TYPE = new Type<ExpenseAddedEventHandler>();

  private final Expense result;

  public ExpenseAddedEvent(Expense result) {

    this.result = result;
  }

  public Type<ExpenseAddedEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(ExpenseAddedEventHandler handler) {
    handler.onExpenseAdded(this);
  }
}
