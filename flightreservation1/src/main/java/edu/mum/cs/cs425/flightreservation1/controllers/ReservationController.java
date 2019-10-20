package edu.mum.cs.cs425.flightreservation1.controllers;


import edu.mum.cs.cs425.flightreservation1.domains.Flight;
import edu.mum.cs.cs425.flightreservation1.domains.Reservation;
import edu.mum.cs.cs425.flightreservation1.dto.ReservationRequest;
import edu.mum.cs.cs425.flightreservation1.exceptions.FlightNotFound;
import edu.mum.cs.cs425.flightreservation1.repositories.FlightRepository;
import edu.mum.cs.cs425.flightreservation1.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ReservationController {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationService reservationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap){
        LOGGER.info("showCompleteReservation() invoked with the Flight Id: " + flightId);
        Optional<Flight> flight=flightRepository.findById(flightId);
        // handle error here
        if(!flight.isPresent()){
            LOGGER.error("Flight Not found: {}",flight.toString());
            throw new FlightNotFound("flightId "+flightId);
        }
        LOGGER.info("Flight found: {}",flight.toString());
        modelMap.addAttribute("flight",flight.get());
        return "reservation/completeReservation";
    }

    @RequestMapping(value = "/completeReservation",method = RequestMethod.POST)
    public String completeReservation(ReservationRequest reservationRequest, ModelMap modelMap){
        LOGGER.info("completeReservation() invoked with the Reservation: " + reservationRequest.toString());
        Reservation reservation=reservationService.bookFlight(reservationRequest);
        modelMap.addAttribute("msg","Reservation created successfully and the reservation id is "+reservation.getId());
        return "reservation/reservationConfirmation";
    }

}
