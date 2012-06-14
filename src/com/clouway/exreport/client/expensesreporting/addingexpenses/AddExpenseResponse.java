package com.clouway.exreport.client.expensesreporting.addingexpenses;

import com.clouway.exreport.shared.Expense;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AddExpenseResponse implements Response {
  private Expense expense;

  public AddExpenseResponse(Expense expense) {
    this.expense = expense;
  }

  public Expense getExpense() {
    return expense;
  }
}
