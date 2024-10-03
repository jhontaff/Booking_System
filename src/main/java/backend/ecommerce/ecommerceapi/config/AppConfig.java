package backend.ecommerce.ecommerceapi.config;

import backend.ecommerce.ecommerceapi.mapper.*;
import backend.ecommerce.ecommerceapi.service.booking.ExtraResourceService;
import backend.ecommerce.ecommerceapi.service.room.RoomService;
import backend.ecommerce.ecommerceapi.service.room.RoomTypeService;
import backend.ecommerce.ecommerceapi.service.user.UserService;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    private final UserDetailsService userDetailsService;

    public AppConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public BookingMapper bookingMapper(UserService userService,
                         ExtraResourceMapper extraResourceMapper,
                         RoomMapper roomMapper,
                         UserMapper userMapper) {
        return new BookingMapper(userService, extraResourceMapper, roomMapper, userMapper);
    }

    @Bean
    public ExtraResourceMapper extraResourceMapper (ExtraResourceService extraResourceService) {
        return new ExtraResourceMapper(extraResourceService);
    }

    @Bean
    public RoomMapper roomMapper(RoomService roomService) {
        return new RoomMapper(roomService);
    }

    @Bean
    public RoomTypeMapper roomTypeMapper(RoomTypeService roomTypeService) {
        return new RoomTypeMapper(roomTypeService);
    }

    @Bean
    public UserMapper userMapper(UserService userService, RoleMapper roleMapper) {
        return new UserMapper(userService, roleMapper);
    }

    @Bean
    public RoleMapper roleMapper() {
        return new RoleMapper();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public GoogleAuthenticator googleAuthenticator() {
        GoogleAuthenticatorConfig config = new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                .setTimeStepSizeInMillis(180_000)
                .build();

        return new GoogleAuthenticator(config);
    }

    @Bean
    public GoogleAuthenticatorKey googleAuthenticatorKey(GoogleAuthenticator googleAuthenticator) {
        return googleAuthenticator.createCredentials();
    }


}
