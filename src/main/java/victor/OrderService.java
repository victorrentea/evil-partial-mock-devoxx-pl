package victor;

public class OrderService {

	private OrderRepository repo;

	public void placeOrder(Order order) {
		repo.save(order);
	}

	public void setOrderRepository(OrderRepository repo) {
		this.repo = repo;
	}

}
