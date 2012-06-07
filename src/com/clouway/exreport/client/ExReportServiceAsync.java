package com.clouway.exreport.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ExReportServiceAsync {
  void getMessage(String msg, AsyncCallback<String> async);
}
