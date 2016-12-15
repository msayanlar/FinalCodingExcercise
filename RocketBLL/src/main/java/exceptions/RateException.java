package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	RateDomainModel rates;

	public RateException(RateDomainModel i) {
		this.rates = i;
	}

	public RateDomainModel getRates() {
		return rates;
	}

}
