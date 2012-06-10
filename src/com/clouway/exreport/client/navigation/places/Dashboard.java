package com.clouway.exreport.client.navigation.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class Dashboard extends Place {


  public static class Tokenizer implements PlaceTokenizer<Dashboard> {

    @Override
    public Dashboard getPlace(String token) {
      return new Dashboard();
    }

    @Override
    public String getToken(Dashboard place) {
      return "expensesdashboard";
    }
  }
}
