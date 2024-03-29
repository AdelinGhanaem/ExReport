package com.clouway.exreport.shared.reponses;

import com.clouway.exreport.shared.entites.Month;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchMonthsResponse implements Response,IsSerializable {

  public FetchMonthsResponse() {
  }

  public FetchMonthsResponse(ArrayList<Month> months) {
    this.months = months;
  }

  private ArrayList<Month> months;

  public ArrayList<Month> getMonths() {
    return months;
  }
}
