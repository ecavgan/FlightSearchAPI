package com.aviation.flightsearch.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aviation.flightsearch.entity.Airport;
import com.aviation.flightsearch.entity.Flight;

@RestController
@RequestMapping(path = "/api/mock")
public class MockController {
	
	@GetMapping(path = "/getExternalFlights")
	public List<Flight> getExternalFlights() {
		List<Flight> flights = new ArrayList<>();
		
		Airport ankaraAirport = new Airport();
		ankaraAirport.setCity("Ankara");
		
		Airport istanbulAirport = new Airport();
		istanbulAirport.setCity("İstanbul");
		
		Airport izmirAirport = new Airport();
		izmirAirport.setCity("İzmir");
		
		Flight f1 = new Flight();		
		f1.setDepartureAirport(ankaraAirport);
		f1.setArrivalAirport(istanbulAirport);
		f1.setDepartureDate(LocalDateTime.of(2023, Month.SEPTEMBER, 5, 8, 00, 00));
		f1.setReturnDate(LocalDateTime.of(2023, Month.SEPTEMBER, 5, 11, 00, 00));
		f1.setPrice(new BigDecimal(300));
		
		Flight f2 = new Flight();		
		f2.setDepartureAirport(istanbulAirport);
		f2.setArrivalAirport(ankaraAirport);
		f2.setDepartureDate(LocalDateTime.of(2023, Month.SEPTEMBER, 6, 20, 00, 00));
		f2.setReturnDate(LocalDateTime.of(2023, Month.SEPTEMBER, 6, 23, 00, 00));
		f2.setPrice(new BigDecimal(300));
		
		Flight f3 = new Flight();		
		f3.setDepartureAirport(izmirAirport);
		f3.setArrivalAirport(ankaraAirport);
		f3.setDepartureDate(LocalDateTime.of(2023, Month.SEPTEMBER, 5, 19, 30, 00));
		f3.setReturnDate(LocalDateTime.of(2023, Month.SEPTEMBER, 5, 21, 30, 00));
		f3.setPrice(new BigDecimal(310));
		
		Flight f4 = new Flight();		
		f4.setDepartureAirport(izmirAirport);
		f4.setArrivalAirport(istanbulAirport);
		f4.setDepartureDate(LocalDateTime.of(2023, Month.SEPTEMBER, 7, 9, 30, 00));
		f4.setReturnDate(LocalDateTime.of(2023, Month.SEPTEMBER, 7, 13, 30, 00));
		f4.setPrice(new BigDecimal(390));
		
		flights.add(f1);
		flights.add(f2);
		flights.add(f3);
		flights.add(f4);
		
		return flights;
	}
}
