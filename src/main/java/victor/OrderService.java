package victor;

import java.util.UUID;

public class OrderService {

	// @Inject/@Autowire
	private OrderRepository repo;

	private EmailSender emailSender;
	
	public String placeOrder(Order order) {
		String confirmationNumber = UUID.randomUUID().toString();
		order.setConfirmationNumber(confirmationNumber);
		repo.save(order);
		Email email = new Email();
		email.setSubject("Order Received");
		email.setBody("Thank you, " + order.getCustomerName().toUpperCase());
		emailSender.send(email);
		return confirmationNumber;
	}

}
