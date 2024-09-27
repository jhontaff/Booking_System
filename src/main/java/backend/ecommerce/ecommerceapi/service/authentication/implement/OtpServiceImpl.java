package backend.ecommerce.ecommerceapi.service.authentication.implement;

import backend.ecommerce.ecommerceapi.config.exception.OtpException;
import backend.ecommerce.ecommerceapi.service.authentication.OtpService;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OtpServiceImpl implements OtpService {

    private final Logger logger = LoggerFactory.getLogger(OtpServiceImpl.class);
    private final GoogleAuthenticator gAuth;


    private Map<String, String> otpMap = new HashMap<>();

    public OtpServiceImpl(GoogleAuthenticator gAuth) {
        this.gAuth = gAuth;
    }

    public String generateOtp(String email) {
        GoogleAuthenticatorKey key = gAuth.createCredentials();
        String secret = key.getKey();
        logger.debug("Generated TOTP: {}", secret);
        int totp = gAuth.getTotpPassword(secret);
        otpMap.put(email, secret);
        return String.valueOf(totp);
    }

    public boolean validateOtp(String email, String otp) {
        String secret = otpMap.get(email);
        if(secret == null) {
            throw new OtpException("No OTP found for this email");
        }
        boolean isTotpValid = gAuth.authorize(secret, Integer.parseInt(otp));
        if (isTotpValid) {
            return true;
        } else {
            throw new OtpException("Invalid OTP");
        }

    }




}

