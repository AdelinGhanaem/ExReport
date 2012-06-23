package com.clouway.exreport.client.navigation.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class NewRegistrationPlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<NewRegistrationPlace> {

    @Override
    public NewRegistrationPlace getPlace(String token) {
      return new NewRegistrationPlace();
    }

    @Override
    public String getToken(NewRegistrationPlace place) {
      return "registration";
    }
  }
}
