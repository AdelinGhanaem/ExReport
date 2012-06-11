package com.clouway.exreport.client.expensesdashboard.view;

import com.google.gwt.user.client.Timer;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class MyAsyncDataProvider extends AsyncDataProvider {
  @Override
  protected void onRangeChanged(HasData display) {
    final Range range = display.getVisibleRange();

    /*
    * Query the data asynchronously. If you are using a database, you can
    * make an RPC call here. We'll use a Timer to simulate a delay.
    */
    new Timer() {
      @Override
      public void run() {
        // We are creating fake data. Normally, the data will come from a
        // server.
        int start = range.getStart();
        int length = range.getLength();
        List<String> newData = new ArrayList<String>();
        for (int i = start; i < start + length; i++) {
          newData.add("Item " + i);
        }

        // Push the data to the displays. AsyncDataProvider will only update
        // displays that are within range of the data.
        updateRowData(start, newData);
      }
    }.schedule(3000);
  }

}
