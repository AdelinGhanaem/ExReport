package com.clouway.exreport.client.navigation.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AuthenticationPlace extends Place {


  public static class Tokenizer implements PlaceTokenizer<AuthenticationPlace> {

    @Override
    public AuthenticationPlace getPlace(String token) {

      return new AuthenticationPlace();
    }

    @Override
    public String getToken(AuthenticationPlace place) {
      return "authentication";
    }
  }

}
