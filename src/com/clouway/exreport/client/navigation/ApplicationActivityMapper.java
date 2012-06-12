package com.clouway.exreport.client.navigation;

import com.clouway.exreport.client.expensesdashboard.expensesreview.ExpenseReporterDashboardPresenter;
import com.clouway.exreport.client.expensesdashboard.expensesreview.view.ExpensesReporterDashboardViewImpl;
import com.clouway.exreport.client.navigation.places.Dashboard;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ApplicationActivityMapper implements ActivityMapper {


  @Override
  public Activity getActivity(Place place) {
    if (place instanceof Dashboard) {
      return new ExpenseReporterDashboardPresenter(new ExpensesReporterDashboardViewImpl(), null);
    }
    return null;
  }

}
