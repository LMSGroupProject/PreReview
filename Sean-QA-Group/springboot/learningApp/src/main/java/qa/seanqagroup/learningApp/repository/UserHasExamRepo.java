package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import qa.seanqagroup.learningApp.model.UserHasExam;

public interface UserHasExamRepo extends JpaRepository<UserHasExam, Long>{

}
