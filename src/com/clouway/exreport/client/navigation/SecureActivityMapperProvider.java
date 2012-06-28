package com.clouway.exreport.client.navigation;

import com.clouway.exreport.client.navigation.activities.AuthenticationActivity;
import com.clouway.exreport.client.navigation.activities.DashboardActivity;
import com.clouway.exreport.client.navigation.activities.NewRegistrationActivity;
import com.clouway.exreport.client.navigation.activities.SuccessfulRegistrationsActivity;
import com.clouway.exreport.client.navigation.places.AuthenticationPlace;
import com.clouway.exreport.client.navigation.places.DashboardPlace;
import com.clouway.exreport.client.navigation.places.NewRegistrationPlace;
import com.clouway.exreport.client.navigation.places.SuccessfulRegistrationsPlace;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecureActivityMapperProvider implements Provider<SecureActivityMapper> {


  @Inject
  DashboardActivity dashboardActivity;

  @Inject
  NewRegistrationActivity newRegistrationActivity;

  @Inject
  AuthenticationActivity authenticationActivity;

  @Inject
  SuccessfulRegistrationsActivity successfulRegistrationsActivity;

  @Inject
  private SecurityTokenProvider provider;

  @Override
  public SecureActivityMapper get() {

    Map<Class<? extends Place>, Activity> activityMap = new HashMap<Class<? extends Place>, Activity>();
    activityMap.put(DashboardPlace.class, dashboardActivity);
    activityMap.put(AuthenticationPlace.class, authenticationActivity);
    activityMap.put(NewRegistrationPlace.class, newRegistrationActivity);
    activityMap.put(SuccessfulRegistrationsPlace.class, successfulRegistrationsActivity);
    List<Class> securityPlaces = new ArrayList<Class>();
    securityPlaces.add(DashboardPlace.class);
    return new SecureActivityMapper(activityMap, securityPlaces, provider);
  }
}
