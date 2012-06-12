package com.clouway.exreport.client.expensesdashboard.addexpenses;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ExpenseAddedEventHandler extends EventHandler {
  void onExpenseAdded(ExpenseAddedEvent event);
}
