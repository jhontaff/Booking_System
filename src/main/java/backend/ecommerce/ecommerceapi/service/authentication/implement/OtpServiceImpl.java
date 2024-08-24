package backend.ecommerce.ecommerceapi.service.authentication.implement;

import backend.ecommerce.ecommerceapi.config.exception.OtpException;
import backend.ecommerce.ecommerceapi.service.authentication.OtpService;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OtpServiceImpl implements OtpService {

    private final Logger logger = LoggerFactory.getLogger(OtpServiceImpl.class);

    private final GoogleAuthenticator gAuth;

    private final GoogleAuthenticatorKey key;

    public OtpServiceImpl(GoogleAuthenticator gAuth, GoogleAuthenticatorKey key) {
        this.gAuth = gAuth;
        this.key = key;
    }

    public String generateOtp() {
        String secret = key.getKey();
        logger.debug("Generated TOTP: {}", secret);
        int totp = gAuth.getTotpPassword(secret);
        return String.valueOf(totp);
    }

    public boolean validateOtp(String otp) {
        boolean isTotpValid = gAuth.authorize(key.getKey(), Integer.parseInt(otp));
        if (isTotpValid) {
            return true;
        } else {
            throw new OtpException("Invalid OTP");
        }

    }




}

