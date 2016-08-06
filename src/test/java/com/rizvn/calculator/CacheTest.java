package com.rizvn.calculator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

/**
 * Created by Riz
 */
public class CacheTest {

  ApplicationContext context;
  SlowCalculator mSlowCalculator;

  @Before
  public void setUp() {
    context = new AnnotationConfigApplicationContext(Config.class);
    mSlowCalculator = context.getBean(SlowCalculator.class);
  }

  @After
  public void teardown() {
    //shut down the Spring context so ehcache shutdown gracefully
    ((ConfigurableApplicationContext) context).close();
  }

  @Test
  public void testCacheCalculation() {
    long start = new Date().getTime();
    System.out.println("Result :" + mSlowCalculator.calculateHypotenuse(4, 5));

    long elapsed = new Date().getTime() - start;
    Assert.assertTrue("Calculation should take longer than 5000ms", elapsed > 5000);

    start = new Date().getTime();
    System.out.println("Result :" + mSlowCalculator.calculateHypotenuse(4, 5));
    elapsed = new Date().getTime() - start;
    Assert.assertTrue("Calculation should take less tahn 5000ms as should be cached", elapsed < 5000);

    start = new Date().getTime();
    System.out.println("Result :" + mSlowCalculator.calculateHypotenuse(4, 5));
    elapsed = new Date().getTime() - start;
    Assert.assertTrue("Calculation should take less than 5000ms", elapsed < 5000);
  }


}
