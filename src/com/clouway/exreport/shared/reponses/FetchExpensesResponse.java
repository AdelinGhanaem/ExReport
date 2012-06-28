package com.clouway.exreport.shared.reponses;

import com.clouway.exreport.shared.entites.Expense;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchExpensesResponse implements Response ,IsSerializable {

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
