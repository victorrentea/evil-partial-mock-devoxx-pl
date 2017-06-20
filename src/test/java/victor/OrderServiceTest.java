package victor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

	@InjectMocks
	private OrderService service;
	
	@Mock
	private OrderRepository orderRepo;
	
	@Test
	public void canPlaceAnOrder() {
		Order order = new Order();
		service.placeOrder(order);
	}

	@Test
	public void placeOrder_peristsTheOrder() {
		Order order = new Order();
		service.placeOrder(order);
		verify(orderRepo).save(order);
	}
	
	@Test
	public void placeOrder_returnsConfirmationNumber() {
		Order order = new Order();
		String confirmation = service.placeOrder(order);
		assertEquals(confirmation, order.getConfirmationNumber());
		assertNotNull(confirmation);
	}
}
