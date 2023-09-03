package com.aviation.flightsearch.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aviation.flightsearch.dto.FlightDTO;
import com.aviation.flightsearch.dto.request.SearchRequest;
import com.aviation.flightsearch.service.FlightService;

@RestController
@RequestMapping(path = "/api/flights")
// @CrossOrigin
public class FlightController {
	
	private FlightService flightService;

	public FlightController(FlightService flightService)
	{
		this.flightService = flightService;
	}
	
	@PostMapping
	public FlightDTO createFlight(@RequestBody FlightDTO flight) {
		return flightService.createFlight(flight);
	}
	
	@GetMapping(path = "/{flightId}")
	public FlightDTO getFlight(@PathVariable Long flightId) {
		return flightService.getFlightById(flightId);
	}
	
	@GetMapping
	public List<FlightDTO> getAllFlights() {
		return flightService.getAllFlights();
	}
	
	@PutMapping(path = "/{flightId}")
	public FlightDTO updateFlight(@PathVariable Long flightId, @RequestBody FlightDTO newFlight) {
		return flightService.updateFlight(flightId, newFlight);
	}
	
	@DeleteMapping(path = "/{flightId}")
	public void deleteAirportById(@PathVariable Long flightId) {
		flightService.deleteFlightById(flightId);
	}
	
	@GetMapping(path = "/searchSuitedFlights")
	public List<List<FlightDTO>> searchSuitedFlights(@RequestBody SearchRequest request) {
		return flightService.searchSuitedFlights(request);
	}
	
	@PutMapping(path = "/setFlights")
	public void setFlightsExternally() {
		flightService.setFlightsExternally();
	}
}
