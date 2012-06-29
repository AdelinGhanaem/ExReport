package com.clouway.exreport.client.comunication;

import com.evo.gad.shared.Response;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public abstract class GotResponse<T extends Response> implements AsyncCallback<T> {

  @Override
  public void onFailure(Throwable caught) {
//    Window.alert(caught.getMessage());


    StringBuilder builder = new StringBuilder();
    caught.getStackTrace();
    for (StackTraceElement elements : caught.getStackTrace()) {
      builder.append(elements.toString());
      builder.append("\n");
    }
    GWT.log("error",caught);
  }

  @Override
  public void onSuccess(T result) {
    gotResponse(result);
  }

  public abstract void gotResponse(T result);

}
