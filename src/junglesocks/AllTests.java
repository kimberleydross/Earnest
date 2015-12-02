package junglesocks;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ OrderPage_AssertElements.class, OrderPage_EnterQuantities_VerifyTotals.class, TaxPercentages.class })
//@SuiteClasses({ OrderPage_AssertElements.class })
public class AllTests {

}
