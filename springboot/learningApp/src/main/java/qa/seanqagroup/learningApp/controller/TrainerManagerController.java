package qa.seanqagroup.learningApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.exceptions.ResourceAlreadyExistsException;
import qa.seanqagroup.learningApp.exceptions.ResourceNotFoundException;
import qa.seanqagroup.learningApp.model.Trainer;
import qa.seanqagroup.learningApp.model.TrainerManager;
import qa.seanqagroup.learningApp.model.User;
import qa.seanqagroup.learningApp.model.enums.E_UserType;
import qa.seanqagroup.learningApp.repository.TrainerManagerRepository;
import qa.seanqagroup.learningApp.repository.UserRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tmc")
public class TrainerManagerController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	TrainerManagerRepository tmRepo;
	
	
//	@GetMapping("/u/id/{id}")
//	public User getUserById(@PathVariable(value="id") Long userID) {
//		User user = userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("USER", "ID", userID));
//		return user;
//	}
//	@GetMapping("/u/e/{e}")
//	public User getUserByEmail(@PathVariable(value="e") String email) {
//		User user = userRepo.findByEmail(email);
//		return user;
//	}
	

	
	
	
	

	
	@PostMapping("/register/{id}")
	public TrainerManager updateToTrainer(@PathVariable(value = "id")Long userId) {
		User tempUser = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("USER", "ID" , userId));

		if(tempUser.getUserType()!=E_UserType.TRAINER)
			throw new ResourceAlreadyExistsException("USER", "ID AND TYPE", userId+" : "+tempUser.getUserType());
		
		TrainerManager newTM = new TrainerManager();
		newTM.setUserId(tempUser.getUserId());
		newTM.setTrainerManagerId(new Long(0));
		
		return tmRepo.save(newTM);
	}
	
}
