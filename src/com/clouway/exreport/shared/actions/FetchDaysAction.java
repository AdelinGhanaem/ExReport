package com.clouway.exreport.shared.actions;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchDaysAction<FetchDaysResponse extends Response> implements Action<FetchDaysResponse> ,IsSerializable {

  private  int year;
  private  int month;

  public FetchDaysAction() {
  }

  public FetchDaysAction(int year, int month) {

    this.year = year;
    this.month = month;
  }

  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }



}
