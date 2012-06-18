package com.clouway.exreport.server.expensesreporting.actionhandlers;

import com.clouway.exreport.server.expensesreporting.ExpensesService;
import com.clouway.exreport.shared.Actions.FetchDaysAction;
import com.clouway.exreport.shared.entites.Day;
import com.clouway.exreport.shared.Reponses.FetchDaysResponse;
import com.evo.gad.dispatch.ActionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchDaysActionHandler implements ActionHandler<FetchDaysAction, FetchDaysResponse> {


  private final ExpensesService service;

  public FetchDaysActionHandler(ExpensesService service) {

    this.service = service;
  }

  @Override
  public FetchDaysResponse handle(FetchDaysAction action) {

    List<Day> list = service.getDays(action.getYear(), action.getMonth());

    FetchDaysResponse response = new FetchDaysResponse(new ArrayList<Day>(list));

    return response;

  }

}
