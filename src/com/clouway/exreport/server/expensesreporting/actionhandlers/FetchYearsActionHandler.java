package com.clouway.exreport.server.expensesreporting.actionhandlers;

import com.clouway.exreport.server.expensesreporting.ExpensesService;
import com.clouway.exreport.shared.actions.FetchYearsAction;
import com.clouway.exreport.shared.reponses.FetchYearsResponse;
import com.clouway.exreport.shared.entites.Year;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchYearsActionHandler implements ActionHandler<FetchYearsAction, FetchYearsResponse> {

  private ExpensesService service;

  @Inject
  public FetchYearsActionHandler(ExpensesService service) {
    this.service = service;
  }

  public FetchYearsActionHandler() {
  }

  @Override
  public FetchYearsResponse handle(FetchYearsAction action) {

    List<Year> yearList = service.getDeclaredYears();
    if (yearList == null) {
      yearList = new ArrayList<Year>();
    }
    return new FetchYearsResponse(new ArrayList<Year>(yearList));
  }
}
