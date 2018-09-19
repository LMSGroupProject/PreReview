package qa.seanqagroup.learningApp.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.h2.util.New;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import qa.seanqagroup.learningApp.LearningAppApplication;
import qa.seanqagroup.learningApp.model.SectionHasVideo;
import qa.seanqagroup.learningApp.model.Video;
import qa.seanqagroup.learningApp.repository.SectionHasVideoRepository;
import qa.seanqagroup.learningApp.repository.VideoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LearningAppApplication.class})
@AutoConfigureMockMvc
public class VideoControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private SectionHasVideoRepository shvRepo;
	
	@Before
	public void clearDB() {
		shvRepo.deleteAll();
	}
	
	@Test
	public void testGetVideos() throws Exception{
		
		SectionHasVideo sectionHasVideo = new SectionHasVideo();
		sectionHasVideo.setSectionId(12);
		sectionHasVideo.setVideoId(8);
		shvRepo.save(sectionHasVideo);
		

		mvc.perform(get("/getVideo/12"))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.ALL_VALUE))
		.andExpect(jsonPath("$[0].name", is("Chi Teach")));
	}

}
