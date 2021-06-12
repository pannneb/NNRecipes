package rs.apps.nn.recipes.controller.v1;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import rs.apps.nn.recipes.api.EnumResponseStatus;
import rs.apps.nn.recipes.api.ResponseData;
import rs.apps.nn.recipes.common.MessageSourceGuessMe;
import rs.apps.nn.recipes.controller.BasicAPIController;
import rs.apps.nn.recipes.model.Word;
import rs.apps.nn.recipes.service.WordService;

@Controller("WordControllerApi")
@RequestMapping(path = "/v1/words")
public class WordController extends BasicAPIController {

	private WordService wordService;

	@Autowired
	private MessageSourceGuessMe messageSource;

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
			handleException(rd, messageSource, e);
		}
		return rd;
	}

	@GetMapping({ "" /* ,"/" */ })
	@ResponseBody
	public List<Word> getWords() {
		String s = messageSource.getMessage("msg.info.getAllWords", null, Locale.UK);
		return wordService.getAllWords();
	}

}
