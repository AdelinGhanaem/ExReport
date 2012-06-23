package com.clouway.exreport.client.navigation.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SuccessfulRegistrationsPlace extends Place {


  public static class Tokenizer implements PlaceTokenizer<SuccessfulRegistrationsPlace> {
    @Override
    public SuccessfulRegistrationsPlace getPlace(String token) {
      return new SuccessfulRegistrationsPlace();
    }
    @Override
    public String getToken(SuccessfulRegistrationsPlace place) {
      return "regsuccess";
    }
  }
}
