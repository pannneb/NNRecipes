package rs.apps.nn.guessme.controller.v1;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import rs.apps.nn.guessme.api.EnumResponseStatus;
import rs.apps.nn.guessme.api.ResponseData;
import rs.apps.nn.guessme.model.Word;
import rs.apps.nn.guessme.service.WordService;

@Controller
@RequestMapping(path = "/v1/word")
public class WordController {

	private WordService wordService;

	public WordController(WordService wordService) {
		super();
		this.wordService = wordService;
	}

	@PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseData createPerson(@RequestBody Word word) {
		ResponseData rd = new ResponseData();
		try {
			wordService.createOrUpdateWordByWord(word);
			rd.setStatus(EnumResponseStatus.RESP_OK.getId());
		} catch (Exception e) {
			rd.setStatus(EnumResponseStatus.RESP_GENERAL_ERR.getId());
			rd.setDescription(e.getMessage());
		}
		return rd;
	}

	@RequestMapping({ "/all", "/all/" })
	@ResponseBody
	public List<Word> getWords() {
		return wordService.getAllWords();
	}

}
