package com.clouway.exreport;

import com.clouway.exreport.server.accountcreation.CreateAccountActionHandler;
import com.clouway.exreport.server.expensesreporting.actionhandlers.AddExpenseActionHandler;
import com.clouway.exreport.server.expensesreporting.actionhandlers.FetchDaysActionHandler;
import com.clouway.exreport.server.expensesreporting.actionhandlers.FetchExpensesActionHandler;
import com.clouway.exreport.server.expensesreporting.actionhandlers.FetchMonthsActionHandler;
import com.clouway.exreport.server.expensesreporting.actionhandlers.FetchYearsActionHandler;
import com.clouway.exreport.shared.Actions.AddExpenseAction;
import com.clouway.exreport.shared.Actions.CreateAccountAction;
import com.clouway.exreport.shared.Actions.FetchDaysAction;
import com.clouway.exreport.shared.Actions.FetchExpensesAction;
import com.clouway.exreport.shared.Actions.FetchMonthsAction;
import com.clouway.exreport.shared.Actions.FetchYearsAction;
import com.evo.gad.dispatch.ActionHandlerMetadata;
import com.google.inject.Provider;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ActionHandlersMetaDateProvider implements Provider<Set<ActionHandlerMetadata>> {

  @Override
  public Set<ActionHandlerMetadata> get() {
    return new TreeSet<ActionHandlerMetadata>() {{

      add(new ActionHandlerMetadata(AddExpenseAction.class, AddExpenseActionHandler.class));

      add(new ActionHandlerMetadata(CreateAccountAction.class, CreateAccountActionHandler.class));

      add(new ActionHandlerMetadata(FetchDaysAction.class, FetchDaysActionHandler.class));

      add(new ActionHandlerMetadata(FetchExpensesAction.class, FetchExpensesActionHandler.class));

      add(new ActionHandlerMetadata(FetchMonthsAction.class, FetchMonthsActionHandler.class));

      add(new ActionHandlerMetadata(FetchYearsAction.class, FetchYearsActionHandler.class));

    }};
  }
}
