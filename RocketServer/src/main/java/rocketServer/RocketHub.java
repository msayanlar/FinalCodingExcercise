package rocketServer;

import java.io.IOException;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;

public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();

	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");

		if (message instanceof LoanRequest) {
			resetOutput();

			LoanRequest lq = (LoanRequest) message;

			int n = lq.getiTerm();
			double f = 0.0;
			boolean t = false;
			double r = lq.getdRate();
			double p = lq.getdAmount();

			

			// Determines the rate with the given credit score
			// If there is an exception, show an error message, then stop
			// processing
			// If no exception, continue

			try {
				lq.setdRate(RateBLL.getRate(lq.getiCreditScore()));

			}

			catch (RateException a) {
				a.printStackTrace();

			}

			// updates lq, and then sends lq back to the caller(s)

			double Pay = RateBLL.getPayment(r, n, p, f, t);
			lq.setdPayment(Pay);
			sendToAll(lq);
		}
	}
}
