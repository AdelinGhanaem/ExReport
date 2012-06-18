package com.clouway.exreport.shared.Actions;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;


/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchYearsAction<FetchYearsResponse> implements Action<Response> {

  private  int year;

  public FetchYearsAction() {

  }

  public FetchYearsAction(int year) {
    this.year = year;
  }


  public int getYear() {

    return year;

  }


}
