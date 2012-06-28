package com.clouway.exreport.shared.reponses;

import com.clouway.exreport.shared.entites.Token;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecurityResponse<T extends Response> implements Response ,IsSerializable{


  public SecurityResponse(T returnedResponse,Token token) {
    this.returnedResponse = returnedResponse;
  }

  private T returnedResponse;

  public SecurityResponse(T response) {

    returnedResponse = response;
  }

  public SecurityResponse() {
  }

  public T getResponse() {
    return returnedResponse;
  }
}
