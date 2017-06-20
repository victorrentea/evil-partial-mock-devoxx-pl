package victor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
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
		order.setCustomerName("Victor");
		service.placeOrder(order);
		verify(orderRepo).save(order);
	}
	
	@Test
	public void placeOrder_returnsConfirmationNumber() {
		order.setCustomerName("Victor");
		String confirmation = service.placeOrder(order);
		assertEquals(confirmation, order.getConfirmationNumber());
		assertNotNull(confirmation);
	}
	
	@Test
	public void placeOrder_sendsConfirmationEmail() {
		order.setCustomerName("Victor");
		service.placeOrder(order);
		ArgumentCaptor<Email> email = ArgumentCaptor.forClass(Email.class);
		verify(emailSender).send(email.capture());
		assertEquals("Order Received", email.getValue().getSubject());
		assertEquals("Thank you, VICTOR", email.getValue().getBody());
	}
	
	@Test
	public void shipOrder_ok() {
		service = spy(OrderService.class);
		doNothing().when(service).sendEmail((Order)anyObject(), anyString());
		service.shipOrder(order);
		assertTrue(order.isShipped());
		verify(service).sendEmail(order, "Order Shipped");
	}
}
