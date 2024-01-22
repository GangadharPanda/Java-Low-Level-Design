package designpatterns.structural.adapter;

public class PayUApi {

	public void sendMoney(long id, Double amount) {

		System.out.println(" -- Calling the PayUAPI ---");

		System.out.println(" -- Initiating the txn ---");

		System.out.println(" -- Amount sent ---" + amount);
	}

	public PayUPaymentStatus fetchStatus(long id) {
		System.out.println("Please wait while we fetch the txn detail from PayUAPI ");
		return PayUPaymentStatus.COMPLETED;
	}

}
