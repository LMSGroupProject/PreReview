package qa.seanqagroup.learningApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import qa.seanqagroup.learningApp.model.Section;
import qa.seanqagroup.learningApp.repository.SectionRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SectionController {

	@Autowired
	private SectionRepository sectionRepository;

	@PostMapping("/section/add")
	public String createSection(Section section) {
//		System.out.println(section.getSectionName());
//		System.out.println(section.getSectionContent());
//		System.out.println(section.getModuleId());
		sectionRepository.save(section);
		sectionRepository.findAll().forEach(Value -> System.out.println(Value.getSectionId()));
		long id = sectionRepository.findAll().get(sectionRepository.findAll().size() - 1).getSectionId();
//		System.out.println(id);
//		return id;
		Gson gson = new Gson();
		String store = gson.toJson("{'sectionid':" + id + "}");
		System.out.println(store);
		return store;
	}

	@PostMapping("/section/youtube")
	public void addYoutube(@RequestBody String value) {
		System.out.println(value);
	}
}
