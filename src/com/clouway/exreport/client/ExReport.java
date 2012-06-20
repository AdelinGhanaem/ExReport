package com.clouway.exreport.client;

import com.clouway.exreport.client.navigation.places.AuthenticationPlace;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class ExReport implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    SimpleLayoutPanel panel = new SimpleLayoutPanel();

    GinInjector injector = GWT.create(GinInjector.class);

    PlaceController controller = injector.placeController();

    ActivityManager manager = injector.activityManager();

    manager.setDisplay(panel);

    RootLayoutPanel.get().add(panel);

    controller.goTo(new AuthenticationPlace());

  }

}
