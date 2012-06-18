package com.clouway.exreport.server.expensesreporting.actionhandlers;

import com.clouway.exreport.server.expensesreporting.ExpensesService;
import com.clouway.exreport.shared.actions.AddExpenseAction;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.reponses.AddExpenseResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AddExpenseActionHandler implements ActionHandler<AddExpenseAction, AddExpenseResponse> {


  private final ExpensesService expensesService;

  @Inject
  public AddExpenseActionHandler(ExpensesService expensesService) {

    this.expensesService = expensesService;

  }


  @Override
  public AddExpenseResponse handle(AddExpenseAction action) {
    Expense expense = expensesService.add(action.getExpense());
    if (expense != null) {
      return new AddExpenseResponse(action.getExpense());
    }
    return null;
  }
}
