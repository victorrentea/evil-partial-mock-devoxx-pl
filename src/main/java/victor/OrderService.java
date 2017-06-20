package victor;

import java.util.UUID;

public class OrderService {

	// @Inject/@Autowire
	private OrderRepository repo;

	// @Inject/@Autowire
	private EmailService emailService;
	
	public String placeOrder(Order order) {
		String confirmationNumber = UUID.randomUUID().toString();
		order.setConfirmationNumber(confirmationNumber);
		repo.save(order);
		emailService.sendEmail(order, "Order Received");
		return confirmationNumber;
	}

	public void shipOrder(Order order) {
		order.setShipped();
		emailService.sendEmail(order, "Order Shipped");
	}

}
