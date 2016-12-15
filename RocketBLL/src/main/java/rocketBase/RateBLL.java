package rocketBase;

import org.apache.poi.ss.formula.functions.*;

import java.util.ArrayList;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();

	public static double getRate(int GivenCreditScore) throws RateException

	{

		double interestRate = 0.0;

		ArrayList<RateDomainModel> rate = RateDAL.getAllRates();

		for (RateDomainModel c : rate) {
			if (GivenCreditScore == c.getiMinCreditScore()) {
				interestRate = c.getdInterestRate();
			} else if (rate.contains(GivenCreditScore)) {
				continue;
			} else {
				throw new RateException(c);
			}
		}

		return interestRate;

	}

	public static double getPayment(double r, double n, double p, double f, boolean t) {
		return FinanceLib.pmt(r, n, p, f, t);
	}
}
