package com.clouway.exreport.shared.actions;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchMonthsAction<FetchMonthResponse extends Response> implements Action<FetchMonthResponse> ,IsSerializable {

  private  int year;

  public FetchMonthsAction() {
  }

  public FetchMonthsAction(int year) {

    this.year = year;

  }

  public int getYear() {

    return year;

  }

}
