package com.clouway.exreport.shared.actions;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

import java.io.Serializable;


/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchYearsAction<FetchYearsResponse> implements Action<Response> ,Serializable {

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
