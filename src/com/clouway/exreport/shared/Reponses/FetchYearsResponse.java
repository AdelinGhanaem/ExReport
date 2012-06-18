package com.clouway.exreport.shared.reponses;

import com.clouway.exreport.shared.entites.Year;
import com.evo.gad.shared.Response;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchYearsResponse implements Response {

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
