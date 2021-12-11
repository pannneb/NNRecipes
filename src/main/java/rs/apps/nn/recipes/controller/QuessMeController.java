package rs.apps.nn.recipes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;
import rs.apps.nn.recipes.domain.Word;
import rs.apps.nn.recipes.service.WordService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/guessME")
@Slf4j
public class QuessMeController {

	protected final Logger log1 = LoggerFactory.getLogger(this.getClass().getCanonicalName());

	
	@Autowired
	private WordService wordService;

//  private JokesService jokesService;
//
//  @Autowired
//  public JokesController(JokesService jokesService) {
//      this.jokesService = jokesService;
//  }

	public void setWordService(WordService wordService) {
		this.wordService = wordService;
	}

	@RequestMapping(value = { "/nextWord", "/", "" }, method = RequestMethod.GET)
	public String showNextWord(Model model) {
		log.info("start - info");
		log.debug("start - debug");

		// String nextJoke = jokesService.getRandomJoke();
		// model.addAttribute("joke", nextJoke);
		// System.out.println("Returning joke:"+nextJoke);
		return "next word is: Sun";
	}

	@GetMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> sayHello() {
		// Get data from service layer into entityList.

		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.createObjectNode();

		JsonNode childNode1 = mapper.createObjectNode();
		((ObjectNode) childNode1).put("name1", "val1");
		((ObjectNode) childNode1).put("name2", "val2");

		((ObjectNode) rootNode).set("obj1", childNode1);

		JsonNode childNode2 = mapper.createObjectNode();
		((ObjectNode) childNode2).put("name3", "val3");
		((ObjectNode) childNode2).put("name4", "val4");

		((ObjectNode) rootNode).set("obj2", childNode2);

		JsonNode childNode3 = mapper.createObjectNode();
		((ObjectNode) childNode3).put("name5", "val5");
		((ObjectNode) childNode3).put("name6", "val6");

		((ObjectNode) rootNode).set("obj3", childNode3);

		String jsonString = "";
		try {
			jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
			System.out.println(jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// List<JSONObject> entitiesJson = new ArrayList<JSONObject>();
//      for (Entity n : entityList) {
//          JSONObject entity = new JSONObject();
//          entity.put("aa", "bb");
//          entities.add(entity);
//      }
		List<String> entities = new ArrayList<String>();
		entities.add("AAA");
		entities.add("bbb");
		return new ResponseEntity<Object>(jsonString, HttpStatus.OK);

	}

	// @GetMapping(path = "/helloJson", produces=MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getWord", method = RequestMethod.GET, produces = "application/json")
	public Word getWord() {
		Word w = wordService.getWordByWordHQL("ples");
		return w;
	}

}
