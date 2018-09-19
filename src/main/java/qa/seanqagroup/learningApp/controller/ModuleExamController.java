package qa.seanqagroup.learningApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import qa.seanqagroup.learningApp.exceptions.ResourceNotFoundException;
import qa.seanqagroup.learningApp.model.Answer;
import qa.seanqagroup.learningApp.model.ModuleExam;
import qa.seanqagroup.learningApp.model.TestQuestionModel;
import qa.seanqagroup.learningApp.repository.AnswerRepo;
import qa.seanqagroup.learningApp.repository.ModuleExamRepo;
import qa.seanqagroup.learningApp.repository.TestQuestionRepository;

@RestController
public class ModuleExamController {

	@Autowired
	ModuleExamRepo testRepo;

	@Autowired
	TestQuestionRepository testQRepo;

	@Autowired
	AnswerRepo answerRepo;

	@PostMapping("/newtest")
	public ModuleExam createTest(ModuleExam test) {
		return testRepo.save(test);
	}

	@PostMapping("/TestModel")
	public void createTest(@RequestBody String payload) {
		try {
			System.out.println(payload);
			JsonParser parser = new JsonParser();
			JsonArray arr = parser.parse(payload).getAsJsonArray();
			ModuleExam exam = new ModuleExam();
			Long currentQuestion = (long) 0;

			for (JsonElement json : arr) {
				TestQuestionModel questions = new TestQuestionModel();
				Answer answers = new Answer();
				JsonObject testNameObj = json.getAsJsonObject();
				String category = testNameObj.get("title").getAsString();
				String inputs = testNameObj.get("value").getAsString();

				if (category.equals("test_name")) {
					exam.setTestName(testNameObj.get("value").toString().replace("\"", ""));

				} else if (category.equals("totalMarks")) {
					exam.setTotalMarks((long) Integer.parseInt(testNameObj.get("value").toString().replace("\"", "")));

				}

				else if (category.equals("testDescription")) {
					exam.setTestDescription(testNameObj.get("value").toString().replace("\"", ""));
					exam.setModuleId((long) 3);
					testRepo.save(exam);
				}

				else if (category.indexOf("QC") != -1) {
					questions.setQuestionContent(testNameObj.get("value").toString().replaceAll("\"", ""));
					questions.setTestId(exam.getTestId());
					testQRepo.save(questions);
					currentQuestion = questions.getTestQuestionId();
				} else if (category.endsWith("a")) {
					answers.setAnswerContent(testNameObj.get("value").toString().replaceAll("\"", ""));
					answers.setCorrect(true);
					answers.setTestQuestionId(currentQuestion);
					answerRepo.save(answers);

				} else if (category.endsWith("b")) {
					if (inputs.equals("")) { // if the answer does not have any input.
					} else {
						// Because my JSON comes in a strange way, need to account for the speech marks
						// that come with the answer identification.
						answers.setAnswerContent(testNameObj.get("value").toString().replaceAll("\"", ""));
						answers.setCorrect(false);
						answers.setTestQuestionId(currentQuestion);
						answerRepo.save(answers);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/gettest/{id}")
	public ModuleExam getExam(@PathVariable(value = "id") Long examId) {
		ModuleExam exam = testRepo.findById(examId)
				.orElseThrow(() -> new ResourceNotFoundException("Exam", "Id", examId));
		;
		return exam;
	}

}
