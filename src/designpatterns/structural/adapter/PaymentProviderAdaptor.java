package designpatterns.structural.adapter;

public interface PaymentProviderAdaptor {

	void sendMoney(RequestParameters params);

	public PaymentStatus getStatus(long id);
}
