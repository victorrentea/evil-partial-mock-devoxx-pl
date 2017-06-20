package victor;

import java.util.UUID;

public class OrderService {

	// @Inject/@Autowire
	private OrderRepository repo;

	// @Inject/@Autowire
	private EmailSender emailSender;
	
	public String placeOrder(Order order) {
		String confirmationNumber = UUID.randomUUID().toString();
		order.setConfirmationNumber(confirmationNumber);
		repo.save(order);
		sendEmail(order, "Order Received");
		return confirmationNumber;
	}

	void sendEmail(Order order, String subject) {
		Email email = new Email();
		email.setSubject(subject);
		email.setBody("Thank you, " + order.getCustomerName().toUpperCase());
		emailSender.send(email);
	}
	
	public void shipOrder(Order order) {
		order.setShipped();
		sendEmail(order, "Order Shipped");
	}

}
