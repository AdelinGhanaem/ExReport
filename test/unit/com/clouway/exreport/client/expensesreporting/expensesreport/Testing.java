package com.clouway.exreport.client.expensesreporting.expensesreport;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class Testing {

  private class Driver {

    private final CarFactory factory;

    public Driver(CarFactory factory, AnotherMock mock) {

      this.factory = factory;
    }

    public void drive() {
      Car car = factory.getCar();
      mock.doSomething(car);
    }
  }

  private class Car {
  }

  private interface AnotherMock {
    void doSomething(Car car);
  }

  private interface CarFactory {
    Car getCar();
  }

  @Mock
  CarFactory factory;

  @Mock
  AnotherMock mock;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
  }

  @Test
  public void name() throws Exception {
    Car car = new Car();

    when(factory.getCar()).thenReturn(car);

    Driver driver = new Driver(factory, mock);

    driver.drive();

    verify(mock).doSomething(eq(car));

  }
}
