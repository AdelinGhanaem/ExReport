package com.clouway.exreport.client.expensesreporting.addingexpenses.view;

import com.clouway.exreport.client.expensesreporting.addingexpenses.AddExpensesPresenter;
import com.clouway.exreport.client.navigation.View;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AddExpensesView extends View<AddExpensesPresenter> {

  void notifyNegativeExpensePriceValue();

  void notifyUserOfConnectionError();

  Widget asWidget();
}
