package com.clouway.exreport.shared.Actions;

import com.clouway.exreport.shared.Expense;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AddExpenseAction<AddExpenseResponse> implements Action<Response> {

  private Expense expense;

  private Date date;

  public AddExpenseAction() {
  }

  public AddExpenseAction(Expense expense, Date date) {

    this.expense = expense;

    this.date = date;

  }

  public Expense getExpense() {

    return expense;

  }

  public Date getDate() {
    return date;
  }
}
