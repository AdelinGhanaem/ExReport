package com.clouway.exreport.client.navigation.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class DashboardPlace extends Place {


  public static class Tokenizer implements PlaceTokenizer<DashboardPlace> {

    @Override
    public DashboardPlace getPlace(String token) {
      return new DashboardPlace();
    }

    @Override
    public String getToken(DashboardPlace place) {
      return "expensesreporting";
    }
  }
}
