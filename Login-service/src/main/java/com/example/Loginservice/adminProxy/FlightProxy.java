package com.example.Loginservice.adminProxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Loginservice.vo.AvailableFlight;





@FeignClient(name = "FLIGHTSERVICE", url="http://localhost:9001/flight")
public interface FlightProxy {

	@PostMapping("/addflight")
	public String addFlightModel(@RequestBody  AvailableFlight flight);
		
	
	@GetMapping("/viewallflights")
	public List<AvailableFlight> getAllFlights();
		
	@GetMapping("viewflightbyflightNo/{flightNo}")
	public AvailableFlight getFlightByNo(@PathVariable String flightNo);
	
	@DeleteMapping("/deleteflight/{flightNo}")
	public String deleteFlight(@PathVariable String flightNo);
		
	@GetMapping("/findbetween/{flightFrom}/{flightTo}/{date}")
	public List<AvailableFlight> findByLocation(@PathVariable String flightFrom, @PathVariable String flightTo, @PathVariable String date);
	
	@GetMapping("/viewflightbyname/{flightName}")
	public List<AvailableFlight> getFlightsbyname(@PathVariable("flightName") String flightName);
		
	@PutMapping("updateflightbyid/{flightNo}")
	public AvailableFlight updateFlight(@PathVariable String flightNo,@RequestBody AvailableFlight flight); 
		
	 
}
