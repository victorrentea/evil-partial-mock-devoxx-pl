package victor;

public class EmailService {

	// @Inject/@Autowire
	private EmailClient emailClient;
	
	
	public void sendEmail(Order order, String subject) {
		Email email = new Email();
		email.setSubject(subject);
		email.setBody("Thank you, " + order.getCustomerName().toUpperCase());
		emailClient.send(email);
	}
}