package com.example.Loginservice.vo;

import com.example.Loginservice.model.LoginModel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightBookingVo {
	private AvailableFlight  availableFlight;
	private BookingModel bookingModel;
	private LoginModel loginModel;

}
