package edu.mum.cs.cs425.flightreservation1.repositories;


import edu.mum.cs.cs425.flightreservation1.domains.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {
}
