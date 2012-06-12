package com.clouway.exreport.client.expensesdashboard.addexpenses;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AddExpensesView {
  void notifyNegativeExpensePriceValue();

  void notifyExpenseCanNotBeAddedInFutureDate();
}
