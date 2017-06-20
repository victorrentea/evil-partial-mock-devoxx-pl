package victor;

import java.util.UUID;

public class OrderService {

	// @Inject/@Autowire
	private OrderRepository repo;

	public String placeOrder(Order order) {
		String confirmationNumber = UUID.randomUUID().toString();
		order.setConfirmationNumber(confirmationNumber);
		repo.save(order);
		return confirmationNumber;
	}

}
