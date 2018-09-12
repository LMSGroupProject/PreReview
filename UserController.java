package qa.seanqagroup.learningApp.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.model.User;
import qa.seanqagroup.learningApp.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
	
	@Autowired
	private UserRepository userR;
	
	
	@PostMapping("/login")
	public String checkDetails(User user){
		JSONObject obj = new JSONObject();
		for (User everyone : userR.findAll()) {
			System.out.println(everyone.getEmail());
			if(everyone.getEmail().equals(user.getEmail())) {
				if(everyone.getPassword().equals(user.getPassword())){
					obj.put("name",everyone.getFirstName());
					obj.put("id", everyone.getUserId());
					obj.put("type", everyone.getUserType());
					System.out.println("PASS RETURN");
					return obj.toString();
				}
			}
		}
		System.out.println("FAIL RETURN");
		obj.put("result", "fail");
		return obj.toString();
	}

}
