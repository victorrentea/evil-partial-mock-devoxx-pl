package victor;

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
}
