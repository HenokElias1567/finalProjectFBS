package edu.mum.cs.cs425.flightreservation1.exceptions;

public class UserAlreadyRegistered extends RuntimeException{
    public UserAlreadyRegistered(String message) {
        super(message);
    }
}
