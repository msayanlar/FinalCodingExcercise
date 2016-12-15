package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//Check to see if a known credit score returns a known interest rate
	@Test
	public void test_Interest_rate() throws RateException {
		assertTrue(RateBLL.getRate(700) == 4);
		assertTrue(RateBLL.getRate(400) == 5);
		assertTrue(RateBLL.getRate(800) == 6);
		assertTrue(RateBLL.getRate(100) == 3);
		assertTrue(RateBLL.getRate(200) == 2);

	}

	// Check to see if a RateException is thrown if there are no rates for a given
	// credit score
	@Test(expected = RateException.class)
	public void no_rates_test() throws RateException {
		assertTrue(RateBLL.getRate(375) != 0);
		assertTrue(RateBLL.getRate(450) != 0);
		assertTrue(RateBLL.getRate(725) != 0);
	}

	//RateBLL.getPayment -  this makes sure it’s working as it should
	@Test
	public void get_Payment_Test(){
		assert(RateBLL.getPayment(4,360,300000, 0, false) == 1432.25);
	}
}

//