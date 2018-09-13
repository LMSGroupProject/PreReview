package com.example.LMSzuulGatwayProxyLearnerService;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootZuulgatwayproxyStudentServiceApplication {

	@RequestMapping(value = "/echoLearner/{learnerID}")
	public String echoLearnerName(@PathVariable(name = "learnerID") Long learnerID) {
		return "Learner has ID: " + learnerID;
	}

	@RequestMapping(value = "/getLearnerDetails/{learnerID}")
	public Learner getLearnerDetails(@PathVariable(name = "learnerID") Long learnerID) {
		return new Learner(learnerID, "A", "L", "M", "S");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootZuulgatwayproxyStudentServiceApplication.class, args);
	}
}

class Learner {
	
	Long learnerID;
	String firstName;
	String lastName;
	String email;
	String password;

	public Learner(Long learnerID, String firstName, String lastName, String email, String password) {
		super();
		this.learnerID = learnerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public Long getLearnerID() {
		return learnerID;
	}

	public void setLearnerID(Long learnerID) {
		this.learnerID = learnerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
