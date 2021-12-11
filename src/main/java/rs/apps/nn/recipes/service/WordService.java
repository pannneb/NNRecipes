package rs.apps.nn.recipes.service;

import java.util.List;

import javax.validation.ValidationException;

import rs.apps.nn.recipes.domain.Word;
import rs.apps.nn.recipes.exception.ValidateException;

public interface WordService {

	public List<Word> getAllWords();

	public Word getWordById(Long id);

	public Word createOrUpdateWordByWord(Word entity) throws ValidateException;

	public void deleteWordById(Long id) throws ValidationException;

	public Word getWordByWordHQL(String word) throws ValidationException;

}
