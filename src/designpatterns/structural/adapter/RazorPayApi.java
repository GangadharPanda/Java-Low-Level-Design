package designpatterns.structural.adapter;

public class RazorPayApi {

	public void pay(long id, String name, String email, Double amount) {

		System.out.println(" -- Calling the RazorPayAPI ---");

		System.out.println(" -- Initiating the txn ---");

		System.out.println(" -- Amount sent ---");
	}

	public RazorPayPaymentStatus checkStatus(long id) {
		System.out.println("Please wait while we fetch the txn detail RazorPayAPI");

		return RazorPayPaymentStatus.OK;
	}

}
