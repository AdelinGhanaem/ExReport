package com.clouway.exreport.shared.reponses;

import com.clouway.exreport.shared.entites.Year;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchYearsResponse implements Response,IsSerializable {

  private ArrayList<Year> years;

  public FetchYearsResponse() {
  }

  public FetchYearsResponse(ArrayList<Year> years) {
    this.years = years;
  }



  public ArrayList<Year> getYears() {
    return years;
  }
}
