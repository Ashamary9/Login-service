package com.example.Loginservice.adminProxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Loginservice.vo.UserModel;

@FeignClient(name ="USERSERVICE" , url="http://localhost:9003/user/access")
public interface UserProxy {
	
	@GetMapping("/getAllUsers")
	public List<UserModel> getAllUsers();
	
	@GetMapping("/getbyUsername/{username}")
	public UserModel getbyUserName(@PathVariable String username);

}
	