package ibn.achraf.demo.services;

import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ibn.achraf.demo.providers.SwapErrors;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(SwapErrors.AmountTooLowError.class)
    public ResponseEntity<?> handleTooLow(SwapErrors.AmountTooLowError e) {
        return ResponseEntity.badRequest()
                .body(Map.of("error", "Amount too low"));
    }

    @ExceptionHandler(SwapErrors.AmountTooHigh.class)
    public ResponseEntity<?> handleTooHigh(SwapErrors.AmountTooHigh e) {
        return ResponseEntity.badRequest()
                .body(Map.of("error", "Amount too high"));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArg(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(Map.of("error", "IllegalArgumentException"));
    }

    @ExceptionHandler(URISyntaxException.class)
    public ResponseEntity<?> uRISyntaxException(URISyntaxException e) {
        return ResponseEntity.badRequest()
                .body(Map.of("error", "URISyntaxException"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Unexpected error: " + e.getMessage()));
    }
}
