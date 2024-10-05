package backend.ecommerce.ecommerceapi.config.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordDoesNotMatchException.class)
    public ResponseEntity<Map<String, List<String>>> handlePasswordDoesNotMatchException(PasswordDoesNotMatchException exception) {
        List<String> errors = List.of(exception.getMessage());
        return ResponseEntity.badRequest().body(getErrorsMap("Password exception: ", errors));
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, List<String>>>  handleDuplicateEmailException(DuplicateEmailException exception) {
        List<String> errors =  List.of(exception.getMessage());
        return new ResponseEntity<>(getErrorsMap("Email exception: ", errors), new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
                .toList();
        return new ResponseEntity<>(getErrorsMap("Validation exception: ", errors), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, List<String>>> handleBadCredentialsException(BadCredentialsException exception) {
        List<String> errors = List.of(exception.getMessage());
        return new ResponseEntity<>(getErrorsMap("Credentials exception: ", errors), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtMalformedException.class)
    public ResponseEntity<Map<String, List<String>>> handleJwtMalformedException(JwtMalformedException exception) {
        List<String> errors = List.of(exception.getMessage());
        return new ResponseEntity<>(getErrorsMap("Malformed exception: ", errors), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtTokenUnsupportedException.class)
    public ResponseEntity<Map<String, List<String>>> handleJwtTokenUnsupportedException(JwtTokenUnsupportedException exception) {
        List<String> errors = List.of(exception.getMessage());
        return new ResponseEntity<>(getErrorsMap("Token unsupported exception: ", errors), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtTokenExpiredException.class)
    public ResponseEntity<Map<String, List<String>>> handleJwtTokenExpiredException(JwtTokenExpiredException exception) {
        List<String> errors = List.of(exception.getMessage());
        return new ResponseEntity<>(getErrorsMap("Token expired exception: ", errors), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(OtpException.class)
    public ResponseEntity<Map<String, List<String>>> handleOtpException(OtpException exception) {
        List<String> errors = List.of(exception.getMessage());
        return new ResponseEntity<>(getErrorsMap("OTP exception: ", errors), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleUserNotFoundException(EmailNotFoundException exception) {
        List<String> errors = List.of(exception.getMessage());
        return new ResponseEntity<>(getErrorsMap("Email not found exception: ", errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoomException.class)
    public ResponseEntity<Map<String, List<String>>> handleRoomException(RoomException exception) {
        List<String> errors = List.of(exception.getMessage());
        return new ResponseEntity<>(getErrorsMap("Room exception: ", errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookingException.class)
    public ResponseEntity<Map<String, List<String>>> handleBookingException(BookingException exception) {
        List<String> errors = List.of(exception.getMessage());
        return new ResponseEntity<>(getErrorsMap("Booking exception: ", errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoleException.class)
    public ResponseEntity<Map<String, List<String>>> handleRoleException(RoleException exception) {
        List<String> errors = List.of(exception.getMessage());
        return new ResponseEntity<>(getErrorsMap("Role exception: ", errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PdfException.class)
    public ResponseEntity<Map<String, List<String>>> handlePdfException(PdfException exception) {
        List<String> errors = List.of(exception.getMessage());
        return new ResponseEntity<>(getErrorsMap("Pdf exception: ", errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    private Map<String, List<String>> getErrorsMap(String exception, List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
            errorResponse.put(exception, errors);
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        return new ResponseEntity<>("An error has occurred : " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
