package qa.seanqagroup.learningApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.model.Course;
import qa.seanqagroup.learningApp.repository.CourseRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	@PostMapping("/course/add")
	public void createCourse(Course course, @RequestParam("madeByTrainerId") long madeByTrainerId) {
		course.setTrainerId(madeByTrainerId);
		courseRepository.save(course);
	}
}
