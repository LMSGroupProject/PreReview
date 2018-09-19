package qa.seanqagroup.learningApp.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.seanqagroup.learningApp.model.SectionHasVideo;
import qa.seanqagroup.learningApp.model.Video;
import qa.seanqagroup.learningApp.repository.SectionHasVideoRepository;
import qa.seanqagroup.learningApp.repository.VideoRepository;;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class VideoController {
	
	@Autowired
	private VideoRepository videoR;
	
	@Autowired
	private SectionHasVideoRepository shvR;
	
	@GetMapping("/getVideo/{id}")
	//enters the section id
	public String getVideos(@PathVariable String id) throws JSONException {
		JSONObject obj = new JSONObject();
		ArrayList<JSONObject> arr = new ArrayList();
		ArrayList<Long> vidIds = new ArrayList();
		
		ArrayList<SectionHasVideo> vidIs = shvR.findBySectionId(Long.parseLong(id));
		for(int i=0; i<vidIs.size();i++) {
				vidIds.add(vidIs.get(i).getVideoId());
		}
		
		for(Long vids : vidIds) {
			for(Video allVideos : videoR.findAll()) {
				String tobeFound = Long.toString(vids);
				String vidID = Long.toString(allVideos.getVideoId());
				if(tobeFound.equals(vidID)) {
					obj.put("name", allVideos.getVideoName());
					obj.put("url", allVideos.getVideoUrl());
					obj.put("isYoutube", allVideos.isYoutube());
					arr.add(obj);
				} 
			}
		}
		return arr.toString();
	}
		
		
		
		
		
		//		JSONObject obj = new JSONObject();
//		ArrayList<JSONObject> arr = new ArrayList();
//		ArrayList<SectionHasVideo> vidIds = new ArrayList();
//		
//		vidIds = shvR.findBySectionId(Long.parseLong(id));
//		
//		int i = 1;
//		for(Video allVideos : videoR.findAll()) {
//				String tobeFound = Long.toString(vidIds.get(i).getVideoId());
//				String vidID = Long.toString(allVideos.getVideoId());
//				i++;
//				if(tobeFound.equals(vidID)) {
//					obj.put("name", allVideos.getVideoName());
//					obj.put("url", allVideos.getVideoUrl());
//					arr.add(obj);
//				}
//			}
//		return arr.toString();
	
	
	
//	for(Video allVideos : videoR.findAll()) {
//	String strID = Long.toString(allVideos.getTrainerId());
//	if(strID.equals(id) && (allVideos.isYoutube() == true)){
//		obj.put("videoURL", allVideos.getVideoUrl());
//		obj.put("videoName", allVideos.getVideoName());
//		arr.add(obj);
//	}
//}

}