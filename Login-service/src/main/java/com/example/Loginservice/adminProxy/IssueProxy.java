package com.example.Loginservice.adminProxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Loginservice.vo.HelpModel;
import com.example.Loginservice.vo.UserIssueVO;



@FeignClient(name = "HELPSERVICE")
public interface IssueProxy {
	
	@PostMapping("/issue/addIssue")
    public String addissue(@RequestBody HelpModel helpModel);

	@GetMapping("/issue/getAllIssues")
	public List<HelpModel> getAllIssues();
	
	@GetMapping("/issue/getByUsername/{username}")
	public UserIssueVO getByUsername(@PathVariable String username);
	
	
	@PutMapping("/issue/update/{issue}")
	public HelpModel updateIssue(@RequestBody HelpModel helpModel, @PathVariable String issue);
	
	@GetMapping("issue/getUserissues/{username}")
	public List<HelpModel> getUserissues(@PathVariable String username);
}


