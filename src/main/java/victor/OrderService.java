package victor;

public class OrderService {

	// @Inject/@Autowire
	private OrderRepository repo;

	public void placeOrder(Order order) {
		repo.save(order);
	}

}
