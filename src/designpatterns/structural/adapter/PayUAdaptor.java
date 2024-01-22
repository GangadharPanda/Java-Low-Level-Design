package designpatterns.structural.adapter;

public class PayUAdaptor implements PaymentProviderAdaptor {

	PayUApi api;

	PayUAdaptor() {
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
