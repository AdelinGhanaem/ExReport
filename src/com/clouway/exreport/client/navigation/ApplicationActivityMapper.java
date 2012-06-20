package com.clouway.exreport.client.navigation;

import com.clouway.exreport.client.navigation.activities.AuthenticationActivity;
import com.clouway.exreport.client.navigation.activities.DashboardActivity;
import com.clouway.exreport.client.navigation.activities.NewRegistrationActivity;
import com.clouway.exreport.client.navigation.places.AuthenticationPlace;
import com.clouway.exreport.client.navigation.places.DashboardPlace;
import com.clouway.exreport.client.navigation.places.NewRegistrationPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ApplicationActivityMapper<T extends Place> implements ActivityMapper {


  @Inject
  DashboardActivity dashboardActivity;

  @Inject
  NewRegistrationActivity newRegistrationActivity;

  @Inject
  AuthenticationActivity authenticationActivity;

  @Override
  public Activity getActivity(Place place) {

    if (place instanceof DashboardPlace) {
      return dashboardActivity;
    }
    if (place instanceof NewRegistrationPlace) {
      return newRegistrationActivity;
    }
    if (place instanceof AuthenticationPlace) {
      return authenticationActivity;
    }
    return null;
  }
}
