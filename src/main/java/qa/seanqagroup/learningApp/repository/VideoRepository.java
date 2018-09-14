package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import qa.seanqagroup.learningApp.model.Video;

public interface VideoRepository extends JpaRepository<Video,Long> {

}
