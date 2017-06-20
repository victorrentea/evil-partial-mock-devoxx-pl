package victor;

public class Order {

	private String confirmationNumber;
	private String customerName;

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public final String getCustomerName() {
		return customerName;
	}

	public final void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
