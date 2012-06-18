package com.clouway.exreport.server.expensesreporting.actionhandlers;

import com.clouway.exreport.server.expensesreporting.ExpensesService;
import com.clouway.exreport.shared.Actions.FetchYearsAction;
import com.clouway.exreport.shared.Reponses.FetchYearsResponse;
import com.clouway.exreport.shared.entites.Year;
import com.evo.gad.dispatch.ActionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchYearsActionHandler implements ActionHandler<FetchYearsAction, FetchYearsResponse> {

  private final ExpensesService service;

  public FetchYearsActionHandler(ExpensesService service) {

    this.service = service;
  }

  @Override
  public FetchYearsResponse handle(FetchYearsAction action) {

    List<Year> yearList = service.getDeclaredYears();

    return new FetchYearsResponse(new ArrayList<Year>(yearList));
  }
}
