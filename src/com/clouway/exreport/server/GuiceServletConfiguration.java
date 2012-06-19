package com.clouway.exreport.server;

import com.clouway.exreport.client.accountcreation.AccountValidationErrorMessages;
import com.clouway.exreport.server.accountcreation.AccountCreator;
import com.clouway.exreport.server.accountcreation.AccountCreatorImpl;
import com.clouway.exreport.server.accountcreation.AccountRepository;
import com.clouway.exreport.server.accountcreation.AccountRepositoryImpl;
import com.clouway.exreport.server.accountcreation.AccountValidator;
import com.clouway.exreport.server.accountcreation.AccountValidatorImpl;
import com.clouway.exreport.server.accountcreation.actionhandlers.CreateAccountActionHandler;
import com.clouway.exreport.server.comunication.ActionDispatcherServiceImpl;
import com.clouway.exreport.server.expensesreporting.ExpensesRepository;
import com.clouway.exreport.server.expensesreporting.ExpensesService;
import com.clouway.exreport.server.expensesreporting.ExpensesServiceImpl;
import com.clouway.exreport.server.expensesreporting.InMemoryExpensesRepository;
import com.clouway.exreport.server.expensesreporting.actionhandlers.AddExpenseActionHandler;
import com.clouway.exreport.server.expensesreporting.actionhandlers.FetchDaysActionHandler;
import com.clouway.exreport.server.expensesreporting.actionhandlers.FetchExpensesActionHandler;
import com.clouway.exreport.server.expensesreporting.actionhandlers.FetchMonthsActionHandler;
import com.clouway.exreport.server.expensesreporting.actionhandlers.FetchYearsActionHandler;
import com.clouway.exreport.shared.actions.AddExpenseAction;
import com.clouway.exreport.shared.actions.CreateAccountAction;
import com.clouway.exreport.shared.actions.FetchDaysAction;
import com.clouway.exreport.shared.actions.FetchExpensesAction;
import com.clouway.exreport.shared.actions.FetchMonthsAction;
import com.clouway.exreport.shared.actions.FetchYearsAction;
import com.evo.gad.dispatch.ActionHandlerMetadata;
import com.evo.gad.dispatch.ActionHandlerRepository;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
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
//        serve("/index").

        serve("/ExReport/service").with(ActionDispatcherServiceImpl.class);

        bind(ActionHandlerRepository.class).to(LazyActionHandlerRepository.class);

//        bind(ActionDispatcher.class).to(ApplicationActionDispatcher.class);

        bind(ExpensesService.class).to(ExpensesServiceImpl.class);

        bind(ExpensesRepository.class).to(InMemoryExpensesRepository.class);

        bind(AccountRepository.class).to(AccountRepositoryImpl.class);

        bind(AccountCreator.class).to(AccountCreatorImpl.class);

        bind(AccountValidator.class).to(AccountValidatorImpl.class);

        bind(AccountValidationErrorMessages.class).to(AccountValidationErrorMessagesImpl.class);

        bind(ActionHandlerRepository.class).to(LazyActionHandlerRepository.class);
//        bind(Injector.class).to();
      }

      @Provides
      @Singleton
      public Set<ActionHandlerMetadata> metadatas() {

//        return new HashSet<ActionHandlerMetadata>() {{
//
//          add(new ActionHandlerMetadata(AddExpenseAction.class, AddExpenseActionHandler.class));
//
//          add(new ActionHandlerMetadata(CreateAccountAction.class, CreateAccountActionHandler.class));
//
//          add(new ActionHandlerMetadata(FetchDaysAction.class, FetchDaysActionHandler.class));
//
//          add(new ActionHandlerMetadata(FetchExpensesAction.class, FetchExpensesActionHandler.class));
//
//          add(new ActionHandlerMetadata(FetchMonthsAction.class, FetchMonthsActionHandler.class));
//
//          add(new ActionHandlerMetadata(FetchYearsAction.class, FetchYearsActionHandler.class));
//
//        }};
        HashSet<ActionHandlerMetadata> metadatas = new HashSet<ActionHandlerMetadata>();

        metadatas.add(new ActionHandlerMetadata(AddExpenseAction.class, AddExpenseActionHandler.class));
//
        metadatas.add(new ActionHandlerMetadata(CreateAccountAction.class, CreateAccountActionHandler.class));

        metadatas.add(new ActionHandlerMetadata(FetchDaysAction.class, FetchDaysActionHandler.class));

        metadatas.add(new ActionHandlerMetadata(FetchExpensesAction.class, FetchExpensesActionHandler.class));

        metadatas.add(new ActionHandlerMetadata(FetchMonthsAction.class, FetchMonthsActionHandler.class));

        metadatas.add(new ActionHandlerMetadata(FetchYearsAction.class, FetchYearsActionHandler.class));
        return metadatas;

      }
    });
  }


}


