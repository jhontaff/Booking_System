package backend.ecommerce.ecommerceapi.service.authentication;

public interface EmailService {

    public void sendEmail(String to, String subject, String text);
}
