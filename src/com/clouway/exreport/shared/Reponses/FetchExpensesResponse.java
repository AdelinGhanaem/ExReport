package com.clouway.exreport.shared.Reponses;

import com.clouway.exreport.shared.Expense;
import com.evo.gad.shared.Response;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchExpensesResponse implements Response {

  public FetchExpensesResponse(ArrayList<Expense> expenses) {
    this.expenses = expenses;
  }

  public FetchExpensesResponse() {
  }

  private ArrayList<Expense> expenses;

  public ArrayList<Expense> getExpenses() {
    return expenses;
  }
}
