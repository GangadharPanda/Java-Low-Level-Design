package designpatterns.structural.adapter;

public class Runner {

	public static void main(String[] args) {

		PaymentProviderAdapter adapter = new PayUAdapter();
		RequestParameters params = new RequestParameters();
		
		params.setId(1);
		params.setAmount(5000d);
		params.setEmail("testemail");
		params.setName("Gangadhar");

		adapter.sendMoney(params);
		System.out.println(adapter.getStatus(1));
		
		System.out.println("------------------");
		
		adapter = new RazorPayAdapter();
		adapter.sendMoney(params);
		System.out.println(adapter.getStatus(1));

	}

}
