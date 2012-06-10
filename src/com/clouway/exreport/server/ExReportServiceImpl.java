package com.clouway.exreport.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.clouway.exreport.client.ExReportService;

public class ExReportServiceImpl extends RemoteServiceServlet implements ExReportService {
  // Implementation of sample interface method
  public String getMessage(String msg) {
    return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
  }
}