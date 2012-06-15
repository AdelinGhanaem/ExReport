package com.clouway.exreport.server.expensesreporting.actionhandlers;

import com.clouway.exreport.server.expensesreporting.ExpensesService;
import org.junit.Before;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public abstract class ExpensesActionHandlerTest {

  @Mock
  public ExpensesService service;

  @Before
  public void setUp() {
    initMocks(this);
  }
}

