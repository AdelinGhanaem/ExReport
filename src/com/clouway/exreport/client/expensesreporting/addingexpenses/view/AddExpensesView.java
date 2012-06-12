package com.clouway.exreport.client.expensesreporting.addingexpenses.view;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AddExpensesView {
  void notifyNegativeExpensePriceValue();

  void notifyExpenseCanNotBeAddedInFutureDate();

  void notifyUserOfConnectionError();

}
