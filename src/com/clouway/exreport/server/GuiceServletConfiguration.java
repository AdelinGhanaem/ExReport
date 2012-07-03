package com.clouway.exreport.server;

import com.clouway.exreport.server.accountcreation.AccountCreator;
import com.clouway.exreport.server.accountcreation.AccountCreatorImpl;
import com.clouway.exreport.server.accountcreation.AccountRepository;
import com.clouway.exreport.server.accountcreation.AccountRepositoryImpl;
import com.clouway.exreport.server.accountcreation.AccountValidator;
import com.clouway.exreport.server.accountcreation.AccountValidatorImpl;
import com.clouway.exreport.server.accountcreation.actionhandlers.RegisterAccountActionHandler;
import com.clouway.exreport.server.authentication.AuthenticatedUsersRepository;
import com.clouway.exreport.server.authentication.AuthenticatedUsersRepositoryImpl;
import com.clouway.exreport.server.authentication.UserAuthentication;
import com.clouway.exreport.server.authentication.UserAuthenticationActionHandler;
import com.clouway.exreport.server.authentication.UserAuthenticationImpl;
import com.clouway.exreport.server.comunication.ActionDispatcherServiceImpl;
import com.clouway.exreport.server.expensesreporting.ExpensesRepository;
import com.clouway.exreport.server.expensesreporting.ExpensesService;
import com.clouway.exreport.server.expensesreporting.ExpensesServiceImpl;
import com.clouway.exreport.server.expensesreporting.actionhandlers.AddExpenseActionHandler;
import com.clouway.exreport.server.expensesreporting.actionhandlers.FetchDaysActionHandler;
import com.clouway.exreport.server.expensesreporting.actionhandlers.FetchExpensesActionHandler;
import com.clouway.exreport.server.expensesreporting.actionhandlers.FetchMonthsActionHandler;
import com.clouway.exreport.server.expensesreporting.actionhandlers.FetchYearsActionHandler;
import com.clouway.exreport.shared.AccountValidationErrorMessages;
import com.clouway.exreport.shared.AccountValidationErrorMessagesImpl;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.clouway.exreport.shared.actions.AddExpenseAction;
import com.clouway.exreport.shared.actions.RegisterAccountAction;
import com.clouway.exreport.shared.actions.FetchDaysAction;
import com.clouway.exreport.shared.actions.FetchExpensesAction;
import com.clouway.exreport.shared.actions.FetchMonthsAction;
import com.clouway.exreport.shared.actions.FetchYearsAction;
import com.clouway.exreport.shared.actions.UserAuthenticationAction;
import com.evo.gad.dispatch.ActionHandlerMetadata;
import com.evo.gad.dispatch.ActionHandlerRepository;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
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

        serve("/ExReport/service").with(ActionDispatcherServiceImpl.class);

        serve("/test").with(TestServlet.class);

        bind(ActionHandlerRepository.class).to(LazyActionHandlerRepository.class);

        bind(UserAuthentication.class).to(UserAuthenticationImpl.class);

        bind(ExpensesService.class).to(ExpensesServiceImpl.class);

        bind(AccountRepository.class).to(AccountRepositoryImpl.class);

        bind(AccountCreator.class).to(AccountCreatorImpl.class);

        bind(AccountValidator.class).to(AccountValidatorImpl.class);

        bind(AccountValidationErrorMessages.class).to(AccountValidationErrorMessagesImpl.class);

        bind(AuthenticatedUsersRepository.class).to(AuthenticatedUsersRepositoryImpl.class);

        bind(ExpensesRepository.class).toProvider(ExpensesRepositoryProvider.class);

        bind(SecurityTokenProvider.class).to(SecurityTokenProviderImpl.class).in(Singleton.class);

      }

      @Provides
      public DatastoreService provideDatastoreService() {
        return DatastoreServiceFactory.getDatastoreService();
      }


      @Provides
      public MemcacheService provideMemcacheService() {
        return MemcacheServiceFactory.getMemcacheService();
      }

      @Provides
      @Singleton
      public Set<ActionHandlerMetadata> metadatas() {

        HashSet<ActionHandlerMetadata> metadatas = new HashSet<ActionHandlerMetadata>();

        metadatas.add(new ActionHandlerMetadata(AddExpenseAction.class, AddExpenseActionHandler.class));

        metadatas.add(new ActionHandlerMetadata(RegisterAccountAction.class, RegisterAccountActionHandler.class));

        metadatas.add(new ActionHandlerMetadata(FetchDaysAction.class, FetchDaysActionHandler.class));

        metadatas.add(new ActionHandlerMetadata(FetchExpensesAction.class, FetchExpensesActionHandler.class));

        metadatas.add(new ActionHandlerMetadata(FetchMonthsAction.class, FetchMonthsActionHandler.class));

        metadatas.add(new ActionHandlerMetadata(FetchYearsAction.class, FetchYearsActionHandler.class));

        metadatas.add(new ActionHandlerMetadata(UserAuthenticationAction.class, UserAuthenticationActionHandler.class));
        return metadatas;
      }
    });
  }




}


