package victor;

import org.junit.Test;

public class OrderServiceTest {

	@Test
	public void canPlaceAnOrder() {
		OrderService service = new OrderService();
		Order order = new Order();
		service.placeOrder(order);
	}
}
