package edu.mum.cs.cs425.flightreservation1.repositories;


import edu.mum.cs.cs425.flightreservation1.domains.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
