package com.example.Loginservice.vo;

import lombok.Data;

@Data
public class BookingModel {
	
	private String pnr;
	private String username;
	private String flightNo;
	private String phnnumber;
	private String email;
	private int numberOfTickets;

	private int Cost;

}
