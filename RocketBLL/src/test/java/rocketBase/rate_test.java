package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	
	//		Check to see if a known credit score returns a known interest rate
	@Test
	public void testRate(){
		try{
			assertTrue(RateBLL.getRate(1000) == 1.2);
		}catch(RateException r){
			r.printStackTrace();
		}
	}

	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void testPayment(){
		double payment = RateBLL.getPayment(0.6, 10, 12, 1000, false);
		assertTrue(payment == 1000);
	}
}
