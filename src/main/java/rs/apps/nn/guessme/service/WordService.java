package rs.apps.nn.guessme.service;

import java.util.List;

import javax.validation.ValidationException;

import rs.apps.nn.guessme.exception.ValidateException;
import rs.apps.nn.guessme.model.Word;

public interface WordService {

	public List<Word> getAllWords();

	public Word getWordById(Long id);

	public Word createOrUpdateWordByWord(Word entity) throws ValidateException;

	public void deleteWordById(Long id) throws ValidationException;

	public Word getWordByWordHQL(String word) throws ValidationException;

}
