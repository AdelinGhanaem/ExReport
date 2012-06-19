package com.clouway.exreport.shared.actions;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

import java.util.Date;


/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchExpensesAction<FetchExpensesResponse> implements Action<Response> {

  private  Date firstDate;
  private  Date secondDate;

  public FetchExpensesAction() {

  }

  public FetchExpensesAction(Date firstDate, Date secondDate) {

    this.firstDate = firstDate;

    this.secondDate = secondDate;
  }

  public Date getFirstDate() {
    return firstDate;
  }

  public Date getSecondDate() {
    return secondDate;
  }


}
