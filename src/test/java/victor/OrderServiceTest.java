package victor;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;

public class OrderServiceTest {

	@Test
	public void canPlaceAnOrder() {
		OrderService service = new OrderService();
		Order order = new Order();
		service.setOrderRepository(Mockito.mock(OrderRepository.class));
		service.placeOrder(order);
	}

	@Test
	public void placeOrder_peristsTheOrder() {
		OrderService service = new OrderService();
		OrderRepository repoMock = Mockito.mock(OrderRepository.class);
		service.setOrderRepository(repoMock);
		Order order = new Order();
		service.placeOrder(order);
		verify(repoMock).save(order);
		
	}
}
