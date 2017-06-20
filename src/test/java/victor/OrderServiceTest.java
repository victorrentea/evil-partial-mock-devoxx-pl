package victor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

	@InjectMocks
	private OrderService service;
	
	@Mock
	private OrderRepository orderRepo;

	@Mock
	private EmailSender emailSender;
	
	private Order order = new Order();

	@Test
	public void placeOrder_peristsTheOrder() {
		service.placeOrder(order);
		verify(orderRepo).save(order);
	}
	
	@Test
	public void placeOrder_returnsConfirmationNumber() {
		String confirmation = service.placeOrder(order);
		assertEquals(confirmation, order.getConfirmationNumber());
		assertNotNull(confirmation);
	}
	
	@Test
	public void placeOrder_sendsConfirmationEmail() {
		service.placeOrder(order);
		ArgumentCaptor<Email> email = ArgumentCaptor.forClass(Email.class);
		verify(emailSender).send(email.capture());
		assertEquals("Order Received", email.getValue().getSubject());
	}

	
}
