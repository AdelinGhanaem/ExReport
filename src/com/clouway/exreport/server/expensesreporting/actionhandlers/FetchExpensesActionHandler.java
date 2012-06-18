package com.clouway.exreport.server.expensesreporting.actionhandlers;

import com.clouway.exreport.server.expensesreporting.ExpensesService;
import com.clouway.exreport.shared.Actions.FetchExpensesAction;
import com.clouway.exreport.shared.Reponses.FetchExpensesResponse;
import com.clouway.exreport.shared.entites.Expense;
import com.evo.gad.dispatch.ActionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchExpensesActionHandler implements ActionHandler<FetchExpensesAction, FetchExpensesResponse> {

  private final ExpensesService service;

  public FetchExpensesActionHandler(ExpensesService service) {

    this.service = service;
  }

  @Override
  public FetchExpensesResponse handle(FetchExpensesAction action) {

    List<Expense> expenses = service.getExpensesBetween(action.getFirstDate(), action.getSecondDate());

    return new FetchExpensesResponse(new ArrayList<Expense>(expenses));
  }
}
