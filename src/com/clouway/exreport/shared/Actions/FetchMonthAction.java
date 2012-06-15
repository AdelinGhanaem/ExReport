package com.clouway.exreport.shared.Actions;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchMonthAction<FetchMonthResponse> implements Action<Response> {

  private  int year;

  public FetchMonthAction() {
  }

  public FetchMonthAction(int year) {

    this.year = year;

  }

  public int getYear() {

    return year;

  }

}
