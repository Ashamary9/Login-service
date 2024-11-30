package com.example.Loginservice.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Loginservice.adminProxy.BookingProxy;
import com.example.Loginservice.adminProxy.FlightProxy;
import com.example.Loginservice.adminProxy.IssueProxy;
import com.example.Loginservice.model.LoginModel;
import com.example.Loginservice.service.ServiceImpl;
import com.example.Loginservice.vo.AvailableFlight;
import com.example.Loginservice.vo.BookingModel;
import com.example.Loginservice.vo.FlightBookingVo;
import com.example.Loginservice.vo.HelpModel;
import com.example.Loginservice.vo.UserIssueVO;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;




@Slf4j
@RestController
@RequestMapping("/registration/autherization")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorizeController {
	
	@Autowired
	private ServiceImpl serviceImpl;
	
	@Autowired
	private FlightProxy proxy;
	
	@Autowired
	private BookingProxy bookingProxy;
	
	@Autowired
	private IssueProxy issueProxy;

	
	
//Login Crud Operations Started
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<LoginModel>> getAllUsers(){
		log.info("Inside the getAllLUsers of AuthorizeController Class");
		log.info("Retriving the List of Logins form Database");
		return ResponseEntity.ok(serviceImpl.getAllUsers());			
	}
	
	@GetMapping("/getbyUsername/{username}")
	public ResponseEntity<LoginModel> getbyUserName(@PathVariable String username){
		log.info("Inside the getbyusername method of Authorize Controlller");
		log.info("Retriving the userName");
		return ResponseEntity.ok(serviceImpl.getByUsername(username));
	}
	
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@PutMapping("/updatethepassword/{username}")
	public ResponseEntity<LoginModel> updateByUsername(@PathVariable String username, @RequestBody LoginModel Login ){
		log.info("Success Password is Updated");
		log.info("Inside the updateBuUsername method of Authorize Controller Class");
		return ResponseEntity.ok(serviceImpl.updateByUsername(username, Login));
	}
	
	
	@PreAuthorize("hasAuthority('User')")
	@PutMapping("/updateDetails/{username}")
	public ResponseEntity<LoginModel> updateDetailsByUsername(@PathVariable String username,@RequestBody LoginModel loginModel)

	{
		log.info("Inside the updateDetailsByUsername method of Authorize Controller Class");
		return ResponseEntity.ok(serviceImpl.updateDetails(username, loginModel));

	}
	
	
	                   
	

	
  //Flight Crud Operations Started
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/addFlight")
	public String addFlightModel(@RequestBody AvailableFlight flight) {
		log.info("Flight Add method Strated Inside the Authorize Controller Class");
		
		proxy.addFlightModel(flight);
		
		log.info("Flight details Successfully Added using the Login Controller");
		return "Flight with No: "+flight.getFlightNo()+" have been added Successfully";
	}
	
	

	@GetMapping("/viewallflights")
	public List<AvailableFlight>  getAllFlights(){
		log.info("viewalltrains Method Started Inside the Authorize Controller");
		return proxy.getAllFlights();
	}
	
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("viewflightbyflightNo/{flightNo}")
	public AvailableFlight getFlightByNo(@PathVariable String flightNo) {
		log.info("viewflightbyno Method Started Inside the Authorize Controller");
		
		return proxy.getFlightByNo(flightNo);
	}
	
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/viewflightbyname/{flightName}")
	public List<AvailableFlight> getFlightsbyname(@PathVariable String flightName){
		log.info("**************************************************************");
		log.info("viewflightbyname Method Started Inside the Authorize Controller");
		return proxy.getFlightsbyname(flightName);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/deleteflight/{flightNo}")
	public String deleteFlight(@PathVariable String flightNo) {
		log.info("deletetrain Method Started Inside the Authorize Controller");
		proxy.deleteFlight(flightNo);
		log.info("Flight Delete Successfull. ");
		return "Flight with no."+flightNo+" have been deleted";
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("updateflightbyid/{flightNo}")
	public String updateFlight(@PathVariable String flightNo,@RequestBody AvailableFlight flight) {
		log.info("updateFlight Method Started Inside the Authorize Controller");
		proxy.updateFlight(flightNo, flight);
		log.info("Flight with no."+flightNo+" have been updated in side the Authorize Controller");
		return "Flight with no."+flightNo+" have been updated";
	}
	
	
	@GetMapping("/findbetween/{flightFrom}/{flightTo}/{date}")
	public List<AvailableFlight> findByLocation(@PathVariable String flightFrom, @PathVariable String flightTo, @PathVariable String date){
		log.info("findByLocation Method Started Inside the Authorize Controller");
		return proxy.findByLocation(flightFrom, flightTo ,date);
	}
	
	

// booking

	
	
	@PreAuthorize("hasAuthority('User')")
	@PostMapping("/booking/{username}")
	public String bookTicket(@RequestBody BookingModel booking, @PathVariable String username) {
		log.info("bookTicket Method Started Inside the Authorize Controller"+booking);
		booking.setUsername(username);
		return bookingProxy.bookTicket(booking);
	
	}
	
	@GetMapping("/ViewAllBookings")
    public List<BookingModel> viewAllBookings() {
		return bookingProxy.viewAllBookings();
	}


	@DeleteMapping("/cancelingTicketByPnr/{pnr}")
    public String cancelTicket(@PathVariable String pnr) {
		log.info("cancelTicket Method Started Inside the Authorize Controller");
		return bookingProxy.cancelTicket(pnr);
	}
	
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/ViewTicketByPnr/{pnr}") 
	public BookingModel viewByPnr(@PathVariable String pnr) {
		log.info("viewByPnr Method Started Inside the Authorize Controller");
		return bookingProxy.viewByPnr(pnr);
	}
	

	@GetMapping("/viewByUserName/{username}")
	public List<BookingModel> viewByUserName(@PathVariable String username){
		return bookingProxy.viewByUserName(username);
	}
	
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
    @GetMapping("/ViewBookingTicketByItsTrainAndTotalCost/{pnr}")
	public FlightBookingVo getBookingTicketByItsTrainAndTotalCost(@PathVariable String pnr) {
		log.info("getBookingTicketByItsTrainAndTotalCost Method Started Inside the Authorize Controller");
    	return bookingProxy.getBookingTicketByItsFlightAndTotalCost(pnr);
    }
	
	//help
	
	
		@PreAuthorize("hasAuthority('User')")
		@PostMapping("/issue/addIssue/{username}")
	    public String addissue( @RequestBody HelpModel helpModel, @PathVariable String username) {
			log.info("addissue Method Started Inside the Authorize Controller");
			helpModel.setUsername(username);
			return issueProxy.addissue(helpModel);
		}

		@PreAuthorize("hasAnyAuthority('User', 'Admin')")
		@GetMapping("/issue/getAllIssues")
		public List<HelpModel> getAllIssues(){
			log.info("getAllIssues Method Started Inside the Authorize Controller");
			return issueProxy.getAllIssues();
		}
		

		@PreAuthorize("hasAnyAuthority('User', 'Admin')")
		@GetMapping("/issue/getByUsername/{username}")
		public UserIssueVO getByUsername(@PathVariable String username) {
			log.info("getByUsername Method Started Inside the Authorize Controller");
			return issueProxy.getByUsername(username);
		}
		
	
		
		
		@PreAuthorize("hasAuthority('Admin')")
		@PutMapping("/issue/update/{issueId}")
		public HelpModel updateIssue(@RequestBody HelpModel helpModel, @PathVariable String issueId) {
			log.info("updateIssue Method Started Inside the Authorize Controller");
			return issueProxy.updateIssue(helpModel, issueId);
		}
		
		@PreAuthorize("hasAnyAuthority('User', 'Admin')")
		@GetMapping("/issue/getUserissues/{username}")
		public List<HelpModel> getUserissues(@PathVariable String username){
			return issueProxy.getUserissues(username);
		}
	}


