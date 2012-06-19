package com.clouway.exreport.client.navigation;

import com.clouway.exreport.client.expensesreporting.expensesreport.ExpenseReporterPresenter;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpensesReporterViewImpl;
import com.clouway.exreport.client.navigation.places.DashboardPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ApplicationActivityMapper implements ActivityMapper {


  @Override
  public Activity getActivity(Place place) {
    if (place instanceof DashboardPlace) {
      return new ExpenseReporterPresenter(new ExpensesReporterViewImpl(), null);
    }
    return null;
  }

}
