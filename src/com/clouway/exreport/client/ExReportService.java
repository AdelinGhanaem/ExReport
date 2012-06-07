package com.clouway.exreport.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("exreportservice")
public interface ExReportService extends RemoteService {
  // Sample interface method of remote interface
  String getMessage(String msg);

  /**
   * Utility/Convenience class.
   * Use ExReportService.App.getInstance() to access static instance of ExReportServiceAsync
   */
  public static class App {
    private static ExReportServiceAsync ourInstance = GWT.create(ExReportService.class);

    public static synchronized ExReportServiceAsync getInstance() {
      return ourInstance;
    }
  }
}
