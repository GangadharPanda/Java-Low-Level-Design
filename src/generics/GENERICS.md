#When do we need Adapter Design Pattern

Adapter is a structural design pattern that allows objects with incompatible interfaces to collaborate.


   
   ![alt text](adapter1.png "Image")
   source : refactoring.guru
   
# Problem
Imagine that you’re creating a stock market monitoring app. The app downloads the stock data from multiple sources in XML format and then displays nice-looking charts and diagrams for the user.

At some point, you decide to improve the app by integrating a smart 3rd-party analytics library. But there’s a catch: the analytics library only works with data in JSON format.

   ![alt text](adapter2.png "Image")
   
   You can’t use the analytics library “as is” because it expects the data in a format that’s incompatible with your app.
   
   source : refactoring.guru
   
---

You could change the library to work with XML. However, this might break some existing code that relies on the library. And worse, you might not have access to the library’s source code in the first place, making this approach impossible.

You can create an adapter. This is a special object that converts the interface of one object so that another object can understand it.

An adapter wraps one of the objects to hide the complexity of conversion happening behind the scenes. The wrapped object isn’t even aware of the adapter. For example, you can wrap an object that operates in meters and kilometers with an adapter that converts all of the data to imperial units such as feet and miles.

Adapters can not only convert data into various formats but can also help objects with different interfaces collaborate. Here’s how it works:

The adapter gets an interface, compatible with one of the existing objects.
Using this interface, the existing object can safely call the adapter’s methods.
Upon receiving a call, the adapter passes the request to the second object, but in a format and order that the second object expects.
Sometimes it’s even possible to create a two-way adapter that can convert the calls in both directions.

   ![alt text](adapter3.png "Image")
      source : refactoring.guru
      
      
---

Object adapter
This implementation uses the object composition principle: the adapter implements the interface of one object and wraps the other one. It can be implemented in all popular programming languages.

   ![alt text](adapter4.png "Image")
      source : refactoring.guru
      
      
#Real time example 

Suppose we are building a MoneyPay App.

We need to integrate two APIs RazorPayApi and PayUApi

But both of them have different methods 


for example 

```java 
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

package designpatterns.structural.adapter;

public enum PayUPaymentStatus {
	COMPLETED, FAILED
}

```

But , RazorPayApi 

```java 
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


package designpatterns.structural.adapter;

public enum RazorPayPaymentStatus {
	OK, FAILED
}
```

So the problem here is I will need to write code WHERE I need to check the provider type and call it's 
Api.

to fix this we can create 

1. PaymentProviderAdapter interface 

```java
package designpatterns.structural.adapter;

public interface PaymentProviderAdapter {

	void sendMoney(RequestParameters params);

	public PaymentStatus getStatus(long id);
}
```

2.Create Separate Adapters for both the Payment partners 

```java 
package designpatterns.structural.adapter;

public class RazorPayAdapter implements PaymentProviderAdapter {
	RazorPayApi api;

	RazorPayAdapter() {
		api = new RazorPayApi();
	}

	@Override
	public void sendMoney(RequestParameters params) {
		api.pay(params.getId(), params.getName(), params.getEmail(), params.getAmount());
	}

	@Override
	public PaymentStatus getStatus(long id) {
		RazorPayPaymentStatus status = api.checkStatus(id);
		return to(status);
	}

	private PaymentStatus to(RazorPayPaymentStatus status) {
		switch (status) {
		case OK:
			return PaymentStatus.SUCCESS;
		case FAILED:
			return PaymentStatus.FAILURE;
		default:
			break;
		}
		return null;
	}

}

package designpatterns.structural.adapter;

public class PayUAdapter implements PaymentProviderAdapter {

	PayUApi api;

	PayUAdapter() {
		api = new PayUApi();
	}

	@Override
	public void sendMoney(RequestParameters params) {
		api.sendMoney(params.getId(), params.getAmount());
	}

	@Override
	public PaymentStatus getStatus(long id) {
		PayUPaymentStatus status = api.fetchStatus(id);
		return to(status);
	}

	private PaymentStatus to(PayUPaymentStatus status) {
		switch (status) {
		case COMPLETED:
			return PaymentStatus.SUCCESS;
		case FAILED:
			return PaymentStatus.FAILURE;
		default:
			break;
		}
		return null;
	}

}
```

Runner class

```java

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


Output :

 -- Calling the PayUAPI ---
 -- Initiating the txn ---
 -- Amount sent ---5000.0
Please wait while we fetch the txn detail from PayUAPI 
SUCCESS
------------------
 -- Calling the RazorPayAPI ---
 -- Initiating the txn ---
 -- Amount sent ---
Please wait while we fetch the txn detail RazorPayAPI
SUCCESS

