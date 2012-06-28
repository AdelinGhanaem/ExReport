package com.clouway.exreport.shared.reponses;

import com.clouway.exreport.shared.entites.Day;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchDaysResponse implements Response ,IsSerializable {

  public FetchDaysResponse() {
  }

  private ArrayList<Day> days;

  public FetchDaysResponse(ArrayList<Day> days) {
    this.days = days;
  }

  public ArrayList<Day> getDays() {
    return days;
  }
}
