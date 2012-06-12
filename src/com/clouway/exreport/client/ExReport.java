package com.clouway.exreport.client;

import com.clouway.exreport.client.expensesdashboard.expensesreview.view.ExpensesReporterDashboardViewImpl;
import com.clouway.exreport.client.mainview.CompositePanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class ExReport implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

//
//    SimpleLayoutPanel widgets = new SimpleLayoutPanel();
//
//    EventBus eventBus = new SimpleEventBus();
//
//    PlaceController placeController = new PlaceController(eventBus);
//
//    ActivityMapper mapper = new ApplicationActivityMapper();
//
//    ActivityManager manager = new ActivityManager(mapper, eventBus);
//
//    manager.setDisplay(widgets);
//
//    placeController.goTo(new Dashboard());


        ExpensesReporterDashboardViewImpl dashboardView = new ExpensesReporterDashboardViewImpl();

        CompositePanel compositePanel = new CompositePanel();

        compositePanel.addWidget(dashboardView.asWidget());



//
    RootLayoutPanel.get().add(compositePanel.asWidget());
//
// RootLayoutPanel.get().add(dashboardView.asWidget());


    }
}
