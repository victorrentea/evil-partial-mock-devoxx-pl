package victor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

	@Spy
	@InjectMocks
	private OrderService service;
	
	@Mock
	private OrderRepository orderRepo;

	@Mock
	private EmailService emailService;
	
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
		verify(emailService).sendEmail(order, "Order Received");
//		
		//TODO vrentea: move to EmailServiceTest
//		ArgumentCaptor<Email> email = ArgumentCaptor.forClass(Email.class);
//		verify(emailClient).send(email.capture());
//		assertEquals("Order Received", email.getValue().getSubject());
//		assertEquals("Thank you, VICTOR", email.getValue().getBody());
	}
	
	@Test
	public void shipOrder_ok() {
		service.shipOrder(order);
		assertTrue(order.isShipped());
		
		verify(emailService).sendEmail(order, "Order Shipped");
	}
}
