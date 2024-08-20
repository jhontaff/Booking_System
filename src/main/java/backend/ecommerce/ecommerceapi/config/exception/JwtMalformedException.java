package backend.ecommerce.ecommerceapi.config.exception;

public class JwtMalformedException extends RuntimeException {
    public JwtMalformedException(String message) {
        super(message);
    }

}
