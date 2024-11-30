package com.example.Loginservice.vo;

import java.util.List;

import com.example.Loginservice.model.LoginModel;

import lombok.Data;

@Data
public class UserIssueVO {
	
	private LoginModel loginModel;
	private List<HelpModel> helpModel;

}
