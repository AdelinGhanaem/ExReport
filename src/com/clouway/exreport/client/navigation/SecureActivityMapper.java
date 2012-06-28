package com.clouway.exreport.client.navigation;

import com.clouway.exreport.client.navigation.places.AuthenticationPlace;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.clouway.exreport.shared.entites.Token;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import java.util.List;
import java.util.Map;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecureActivityMapper implements ActivityMapper {

  private SecurityTokenProvider provider;

  private Map<Class<? extends Place>, Activity> activityMap;

  private List<Class> securityPlaces;

  public SecureActivityMapper(Map<Class<? extends Place>, Activity> activityMap, List<Class> securePlaces, SecurityTokenProvider provider) {

    this.activityMap = activityMap;

    securityPlaces = securePlaces;

    this.provider = provider;

  }

  @Override
  public Activity getActivity(Place place) {
    for (Class aClass : securityPlaces) {
      if (aClass.equals(place.getClass())) {
        Token token = provider.getToken();
        if (token == null) {
          return activityMap.get(AuthenticationPlace.class);
        }
      }
    }
    return activityMap.get(place.getClass());
  }
}
