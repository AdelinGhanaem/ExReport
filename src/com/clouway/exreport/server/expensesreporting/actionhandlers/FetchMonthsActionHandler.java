package com.clouway.exreport.server.expensesreporting.actionhandlers;

import com.clouway.exreport.server.expensesreporting.ExpensesService;
import com.clouway.exreport.shared.Actions.FetchMonthsAction;
import com.clouway.exreport.shared.Reponses.FetchMonthsResponse;
import com.clouway.exreport.shared.entites.Month;
import com.evo.gad.dispatch.ActionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchMonthsActionHandler implements ActionHandler<FetchMonthsAction, FetchMonthsResponse> {
  private final ExpensesService service;

  public FetchMonthsActionHandler(ExpensesService service) {

    this.service = service;
  }

  @Override
  public FetchMonthsResponse handle(FetchMonthsAction action) {

    List<Month> months = service.getMonths(action.getYear());
    return new FetchMonthsResponse(new ArrayList<Month>(months));
  }

}
