package com.clouway.exreport.shared.reponses;

import com.clouway.exreport.shared.entites.Expense;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AddExpenseResponse implements Response {
  private Expense expense;

  public AddExpenseResponse(Expense expense) {
    this.expense = expense;
  }

  public AddExpenseResponse() {
  }

  public Expense getExpense() {
    return expense;
  }
}
