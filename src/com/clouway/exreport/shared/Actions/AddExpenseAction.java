package com.clouway.exreport.shared.Actions;

import com.clouway.exreport.shared.entites.Expense;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AddExpenseAction<AddExpenseResponse> implements Action<Response> {

  private Expense expense;


  public AddExpenseAction() {
  }



  public AddExpenseAction(Expense expense) {

    this.expense = expense;
  }

  public Expense getExpense() {
    return expense;

  }


}
