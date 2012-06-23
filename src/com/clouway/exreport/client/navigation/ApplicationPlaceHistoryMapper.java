package com.clouway.exreport.client.navigation;

import com.clouway.exreport.client.navigation.places.AuthenticationPlace;
import com.clouway.exreport.client.navigation.places.DashboardPlace;
import com.clouway.exreport.client.navigation.places.NewRegistrationPlace;
import com.clouway.exreport.client.navigation.places.SuccessfulRegistrationsPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@WithTokenizers({AuthenticationPlace.Tokenizer.class, DashboardPlace.Tokenizer.class, NewRegistrationPlace.Tokenizer.class,
        SuccessfulRegistrationsPlace.Tokenizer.class})
public interface ApplicationPlaceHistoryMapper extends PlaceHistoryMapper {
}
