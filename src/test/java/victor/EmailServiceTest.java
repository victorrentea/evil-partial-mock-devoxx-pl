package victor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {
	
	@InjectMocks
	private EmailService emailService;
	
	@Mock
	private EmailClient emailClient;

	@Test
	public void sendEmailSendsCorrectEmail() {
		Order order = new Order();
		order.setCustomerName("nAmE");
		emailService.sendEmail(order, "Subject");
		ArgumentCaptor<Email> email = ArgumentCaptor.forClass(Email.class);
		verify(emailClient).send(email.capture());
		assertEquals("Subject", email.getValue().getSubject());
		assertEquals("Thank you, NAME", email.getValue().getBody());

	}
}
