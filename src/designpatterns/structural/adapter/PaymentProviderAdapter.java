package designpatterns.structural.adapter;

public interface PaymentProviderAdapter {

	void sendMoney(RequestParameters params);

	public PaymentStatus getStatus(long id);
}
