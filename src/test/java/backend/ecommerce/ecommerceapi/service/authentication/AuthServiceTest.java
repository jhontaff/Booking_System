package backend.ecommerce.ecommerceapi.service.authentication;

import backend.ecommerce.ecommerceapi.config.security.jwt.JwtUtils;
import backend.ecommerce.ecommerceapi.dto.authentication.request.UserLoginDto;
import backend.ecommerce.ecommerceapi.dto.authentication.request.UserRegisterDto;
import backend.ecommerce.ecommerceapi.dto.authentication.response.UserAuthResponseDto;
import backend.ecommerce.ecommerceapi.entity.role.ERole;
import backend.ecommerce.ecommerceapi.entity.role.Role;
import backend.ecommerce.ecommerceapi.entity.user.User;
import backend.ecommerce.ecommerceapi.repository.RoleRepository;
import backend.ecommerce.ecommerceapi.repository.UserRepository;
import backend.ecommerce.ecommerceapi.service.authentication.implement.AuthServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthServiceImpl authService;

    private UserLoginDto userLoginDto;
    private UserRegisterDto userRegisterDto;
    private User user;
    private Role userRole;

    @BeforeEach
    void setUp() {
        userRegisterDto = new UserRegisterDto();
        userRegisterDto.setUsername("Jhon");
        userRegisterDto.setLastname("Doe");
        userRegisterDto.setEmail("jhon123@gmail.com");
        userRegisterDto.setPassword("password");
        userRegisterDto.setConfirmPassword("password");

        userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("jhonedinsontafur@gmail.com");
        userLoginDto.setPassword("Empanada.123");

        userRole = new Role();
        userRole.setRoleName(ERole.USER);

        user = new User();
        user.setEmail("jhonedinsontafur@gmail.com");
        user.setPassword("hashedPassword");
        user.setRoles(new HashSet<>(Set.of(userRole)));

    }

    @AfterEach
    void tearDown() {
        reset(userRepository, roleRepository, jwtUtils, passwordEncoder, authenticationManager);
    }

    @Test
    void passwordMatch() {
        assertTrue(authService.passwordMatch(
                userRegisterDto.getPassword(), userRegisterDto.getConfirmPassword()));
    }

    @Test
    void existsByEmail() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        Boolean existsByEmail = authService.existsByEmail("jhonedinsontafur@gmail.com");
        verify(userRepository, times(1)).findAll();
        assertTrue(existsByEmail);
    }

    @Test
    void validateUserInfo() {
        assertTrue(authService.validateUserInfo(userRegisterDto));
    }

    @Test
    void hashPassword() {
        String rawPassword = userRegisterDto.getPassword();
        String hashedPassword = "$2a$10$hgFkergTtJj8485nWyis";
        when(passwordEncoder.encode(rawPassword)).thenReturn(hashedPassword);
        authService.hashPassword(userRegisterDto);
        verify(passwordEncoder, times(1)).encode(rawPassword);
        assertEquals(hashedPassword, userRegisterDto.getPassword());
    }

    @Test
    void register() {

        when(roleRepository.findByRoleName(ERole.USER)).thenReturn(Optional.of(userRole));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtUtils.getToken(any(UserDetails.class))).thenReturn("jwtToken");

        UserAuthResponseDto response = authService.register(user);

        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());
        verify(userRepository, times(1)).save(user);
    }

    public UserDetails toUserDetails(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRoleName().toString()))
                        .toList())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    @Test
    void login() {
        when(userRepository.findByEmail("jhonedinsontafur@gmail.com")).thenReturn(Optional.of(user));
        when(jwtUtils.getToken(any(UserDetails.class))).thenReturn("jwtToken");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginDto.getEmail(), userLoginDto.getPassword());

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(authenticationToken)).thenReturn(authentication);

        UserAuthResponseDto response = authService.login(userLoginDto);

        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());
        verify(authenticationManager, times(1)).authenticate(authenticationToken);
        verify(userRepository, times(1)).findByEmail(userLoginDto.getEmail());
        verify(jwtUtils, times(1)).getToken(any(UserDetails.class));
    }
}