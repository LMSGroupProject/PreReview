package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import qa.seanqagroup.learningApp.model.Section;

public interface SectionRepository extends JpaRepository<Section,Long> {

}
