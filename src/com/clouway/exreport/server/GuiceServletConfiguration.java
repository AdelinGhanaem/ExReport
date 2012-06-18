package com.clouway.exreport.server;

import com.clouway.exreport.server.accountcreation.CreateAccountActionHandler;
import com.clouway.exreport.server.comunication.ActionDispatcherServiceImpl;
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
import com.evo.gad.dispatch.ActionHandlerRepository;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class GuiceServletConfiguration extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServletModule() {
      @Override
      protected void configureServlets() {

        serve("/ExReport/service").with(ActionDispatcherServiceImpl.class);

        bind(ActionHandlerRepository.class).to(LazyActionHandlerRepository.class);

        bind(ActionHandlerRepository.class).to(LazyActionHandlerRepository.class);

      }

      @Provides
      public Set<ActionHandlerMetadata> metadatas() {

        return new HashSet<ActionHandlerMetadata>() {{

          add(new ActionHandlerMetadata(AddExpenseAction.class, AddExpenseActionHandler.class));

          add(new ActionHandlerMetadata(CreateAccountAction.class, CreateAccountActionHandler.class));

          add(new ActionHandlerMetadata(FetchDaysAction.class, FetchDaysActionHandler.class));

          add(new ActionHandlerMetadata(FetchExpensesAction.class, FetchExpensesActionHandler.class));

          add(new ActionHandlerMetadata(FetchMonthsAction.class, FetchMonthsActionHandler.class));

          add(new ActionHandlerMetadata(FetchYearsAction.class, FetchYearsActionHandler.class));

        }};
      }
    });
  }


}


