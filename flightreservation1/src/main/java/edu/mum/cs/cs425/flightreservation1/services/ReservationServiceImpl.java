package edu.mum.cs.cs425.flightreservation1.services;


import edu.mum.cs.cs425.flightreservation1.domains.Flight;
import edu.mum.cs.cs425.flightreservation1.domains.Passenger;
import edu.mum.cs.cs425.flightreservation1.domains.Reservation;
import edu.mum.cs.cs425.flightreservation1.dto.ReservationRequest;
import edu.mum.cs.cs425.flightreservation1.exceptions.FlightNotFound;
import edu.mum.cs.cs425.flightreservation1.repositories.FlightRepository;
import edu.mum.cs.cs425.flightreservation1.repositories.PassengerRepository;
import edu.mum.cs.cs425.flightreservation1.repositories.ReservationRepository;
import edu.mum.cs.cs425.flightreservation1.util.EmailUtil;
import edu.mum.cs.cs425.flightreservation1.util.PdfGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

//    @Value("${com.flightreservation.flightreservation.itinerary.dirpath}")
    private String ITINERARY_DIR;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    private EmailUtil emailUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);


    @Override
    public Reservation bookFlight(ReservationRequest reservationRequest) {
        // make payment here
        // if the payment is successful proceed..
        LOGGER.info("Inside bookFlight()");
        Long flightId=reservationRequest.getFlightId();
        Optional<Flight> flightOptional=flightRepository.findById(flightId);
        if (!flightOptional.isPresent()) {
            throw new FlightNotFound("No flight found with id "+flightId);
        }
        LOGGER.info("Flight found with id: {}",flightId);
        Flight flight=flightOptional.get();
        Passenger passenger=new Passenger();
        passenger.setFirstName(reservationRequest.getPassengerFirstName());
        passenger.setMiddleName(reservationRequest.getPassengerMiddleName());
        passenger.setLastName(reservationRequest.getPassengerLastName());
        passenger.setEmail(reservationRequest.getPassengerEmail());
        passenger.setPhone(reservationRequest.getPassengerPhone());

        passengerRepository.save(passenger);
        LOGGER.info("Saved the passenger:" + passenger);

        Reservation reservation=new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);
        reservation.setCheckedin(false);
        final Reservation savedReservation = reservationRepository.save(reservation);
        LOGGER.info("Saving the reservation:" + reservation);


        String filePath = ITINERARY_DIR + savedReservation.getId()
                + ".pdf";
        LOGGER.info("Generating  the itinerary");
        pdfGenerator.generateItenary(savedReservation,filePath);
        LOGGER.info("Emailing the Itinerary");
        emailUtil.sendItenary("dlulla@akamai.com",filePath);

        return savedReservation;

    }
}
