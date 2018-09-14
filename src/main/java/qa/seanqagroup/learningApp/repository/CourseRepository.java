package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import qa.seanqagroup.learningApp.model.Course;

public interface CourseRepository extends JpaRepository<Course,Long> {

}
