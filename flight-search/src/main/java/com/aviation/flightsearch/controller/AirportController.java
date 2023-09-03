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

import com.aviation.flightsearch.dto.AirportDTO;
import com.aviation.flightsearch.service.AirportService;

@RestController
@RequestMapping(path = "/api/airports")
public class AirportController {
	
	private AirportService airportService;
	
	public AirportController(AirportService airportService)
	{
		this.airportService = airportService;
	}
	
	@PostMapping
	public AirportDTO createAirport(@RequestBody AirportDTO airport) {
		return airportService.createAirport(airport);
	}
	
	@GetMapping(path = "/{airportId}")
	public AirportDTO getAirport(@PathVariable Long airportId) {
		return airportService.getAirportById(airportId);
	}
	
	@GetMapping
	public List<AirportDTO> getAllAirports() {
		return airportService.getAllAirports();
	}
	
	@PutMapping(path = "/{airportId}")
	public AirportDTO updateAirport(@PathVariable Long airportId, @RequestBody AirportDTO newAirport) {
		return airportService.updateAirport(airportId, newAirport);
	}
	
	@DeleteMapping(path = "/{airportId}")
	public void deleteAirportById(@PathVariable Long airportId) {
		airportService.deleteAirportById(airportId);
	}
}
