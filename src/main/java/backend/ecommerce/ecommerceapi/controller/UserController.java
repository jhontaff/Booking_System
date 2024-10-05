package backend.ecommerce.ecommerceapi.controller;

import backend.ecommerce.ecommerceapi.dto.booking.BookingDto;
import backend.ecommerce.ecommerceapi.dto.role.RoleDto;
import backend.ecommerce.ecommerceapi.dto.user.UserDto;
import backend.ecommerce.ecommerceapi.dto.user.UserRoleDto;
import backend.ecommerce.ecommerceapi.entity.booking.Booking;
import backend.ecommerce.ecommerceapi.entity.role.Role;
import backend.ecommerce.ecommerceapi.entity.user.User;
import backend.ecommerce.ecommerceapi.mapper.BookingMapper;
import backend.ecommerce.ecommerceapi.mapper.UserMapper;
import backend.ecommerce.ecommerceapi.service.role.RoleService;
import backend.ecommerce.ecommerceapi.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final BookingMapper bookingMapper;
    private final RoleService roleService;

    public UserController(UserService userService,
                          UserMapper userMapper,
                          BookingMapper bookingMapper,
                          RoleService roleService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.bookingMapper = bookingMapper;
        this.roleService = roleService;
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = this.userService.getAllUsers();
        List<UserDto> userDtos = users.stream().map(userMapper::toDto).toList();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        User user = this.userService.getUserById(userId);
        UserDto userDto = this.userMapper.toDto(user);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/get-user-bookings/{userId}")
    public ResponseEntity<List<BookingDto>> getUserBookings(@PathVariable Long userId) {
        List<Booking> bookings = this.userService.getUserById(userId).getBookings();
        List<BookingDto> bookingsDto = bookings.stream().map(
                bookingMapper::toDto).toList();
        return ResponseEntity.ok(bookingsDto);
    }

    @GetMapping("/get-user-roles/{userId}")
    public ResponseEntity<Set<RoleDto>> getUserRoles(@PathVariable Long userId) {
        Set<RoleDto> roles = this.userService.getUserRoles(userId);
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/set-user-role")
    public ResponseEntity<Set<RoleDto>> setUserRoles(@RequestBody UserRoleDto userRoleDto) {
        User user = this.userService.getUserById(userRoleDto.getUserId());
        Role role = this.roleService.getRoleById(userRoleDto.getRoleId());
        if (userRoleDto.getPlusMinus().equals(Boolean.TRUE)) {
            user.getRoles().add(role);
        } else {
            user.getRoles().remove(role);
        }
        this.userService.saveUser(user);
        return ResponseEntity.ok(this.userService.getUserRoles(userRoleDto.getUserId()));
    }
}
