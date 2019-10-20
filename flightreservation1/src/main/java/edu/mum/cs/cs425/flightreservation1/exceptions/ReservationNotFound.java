package edu.mum.cs.cs425.flightreservation1.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationNotFound extends RuntimeException {
    public ReservationNotFound(String message) {
        super(message);
    }
}
