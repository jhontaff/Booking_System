package backend.ecommerce.ecommerceapi.config.exception;

public class JwtTokenUnsupportedException extends RuntimeException {
    public JwtTokenUnsupportedException(String message) {
        super(message);
    }
}
