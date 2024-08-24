package backend.ecommerce.ecommerceapi.service.authentication;

public interface OtpService {
    public String generateOtp();
    public boolean validateOtp(String otp);
}