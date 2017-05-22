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
			try {
				double r = RateBLL.getRate(lq.getiCreditScore());
				lq.setdRate(r);
				lq.setdPayment(RateBLL.getPayment(r / 12.0, lq.getiTerm() * 12.0, lq.getdAmount(), 0.0, false));
			} 
			catch (RateException e) {
				System.out.println(e.getMessage());
			}
			
			sendToAll(lq);
		}
	}
}
