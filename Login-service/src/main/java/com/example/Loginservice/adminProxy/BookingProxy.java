package com.example.Loginservice.adminProxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Loginservice.vo.BookingModel;
import com.example.Loginservice.vo.FlightBookingVo;

import jakarta.validation.Valid;



@FeignClient(name = "BOOKINGSERVICE", url = "http://localhost:9002/bookings")
public interface BookingProxy {

	@PostMapping("/booking")
	public String bookTicket(@RequestBody BookingModel booking);
	
	@DeleteMapping("/cancelingTicketByPnr/{pnr}")
    public String cancelTicket(@PathVariable String pnr);
	
    @GetMapping("/ViewAllBookings")
    public List<BookingModel> viewAllBookings();
	
    @GetMapping("/ViewTicketByPnr/{pnr}") 
    public BookingModel viewByPnr(@PathVariable String pnr);
    
    @GetMapping("/viewByUserName/{username}")
    public List<BookingModel> viewByUserName(@PathVariable String username);
    
    @GetMapping("/ViewBookingTicketByItsFlightAndTotalCost/{pnr}")
	 public FlightBookingVo getBookingTicketByItsFlightAndTotalCost(@PathVariable String pnr);
	


	

	

	

}

