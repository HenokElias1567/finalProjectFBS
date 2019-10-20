package edu.mum.cs.cs425.flightreservation1.exceptions;

public class TicketNotFound extends RuntimeException {
    public TicketNotFound(String message) {
        super(message);
    }
}
