package designpatterns.structural.adapter;

public class RazorPayAdaptor implements PaymentProviderAdaptor {
	RazorPayApi api;

	RazorPayAdaptor() {
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
