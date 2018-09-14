package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import qa.seanqagroup.learningApp.model.Module;

public interface ModuleRepository extends JpaRepository<Module,Long>{

}
