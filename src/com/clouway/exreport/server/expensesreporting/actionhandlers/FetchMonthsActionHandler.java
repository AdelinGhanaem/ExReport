package com.clouway.exreport.server.expensesreporting.actionhandlers;

import com.clouway.exreport.server.expensesreporting.ExpensesService;
import com.clouway.exreport.shared.actions.FetchMonthsAction;
import com.clouway.exreport.shared.reponses.FetchMonthsResponse;
import com.clouway.exreport.shared.entites.Month;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchMonthsActionHandler implements ActionHandler<FetchMonthsAction, FetchMonthsResponse> {


  private ExpensesService service;
  @Inject
  public FetchMonthsActionHandler(ExpensesService service) {

    this.service = service;
  }

  public FetchMonthsActionHandler() {
  }

  @Override
  public FetchMonthsResponse handle(FetchMonthsAction action) {

    List<Month> months = service.getMonths(action.getYear());
    return new FetchMonthsResponse(new ArrayList<Month>(months));
  }

}
