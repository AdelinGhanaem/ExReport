package com.clouway.exreport.client.expensesreporting.addingexpenses;

import com.clouway.exreport.shared.Expense;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AddExpenseAction<AddExpenseResponse> implements Action<Response> {

  public AddExpenseAction(Expense expense, Date date) {

  }
}
