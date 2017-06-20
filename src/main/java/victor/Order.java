package victor;

public class Order {

	private String confirmationNumber;
	private String customerName;
	private boolean shipped;

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

	public void setShipped() {
		shipped = true;
	}
	
	public boolean isShipped() {
		return shipped;
	}
	
}
