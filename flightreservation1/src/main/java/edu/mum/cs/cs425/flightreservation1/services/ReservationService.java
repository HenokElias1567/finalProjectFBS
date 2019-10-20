package edu.mum.cs.cs425.flightreservation1.services;


import edu.mum.cs.cs425.flightreservation1.domains.Reservation;
import edu.mum.cs.cs425.flightreservation1.dto.ReservationRequest;

public interface ReservationService {
    public Reservation bookFlight(ReservationRequest reservationRequest);
}
