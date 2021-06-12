package rs.apps.nn.recipes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.apps.nn.recipes.api.EnumResponseStatus;
import rs.apps.nn.recipes.exception.ValidateException;
import rs.apps.nn.recipes.model.Word;
import rs.apps.nn.recipes.repository.WordRepository;

@Service
public class WordServiceImpl implements WordService{

    @Autowired
    WordRepository wordRepository;
     
    @Override
    public List<Word> getAllWords()
    {
        List<Word> wordList = wordRepository.findAll();
         
        if(wordList.size() > 0) {
            return wordList;
        } else {
            return new ArrayList<Word>();
        }
    }
     
    @Override
    public Word getWordById(Long id) throws ValidationException 
    {
        Optional<Word> word = wordRepository.findById(id);
         
        if(word.isPresent()) {
            return word.get();
        } else {
            throw new ValidationException("No word record exist for given id");
        }
    }

	public Word createOrUpdateWordByWord(Word entity) throws ValidateException {
		// Optional<Word> word = wordRepository.findById(entity.getId());
		Optional<Word> word = wordRepository.findByWord(entity.getWord());

		if (word.isPresent()) {
			throw new ValidateException(EnumResponseStatus.RESP_WORD_ALREADY_EXISTS.getId(),
					"msg.err.wordAlreadyExists");
			// Word newEntity = word.get();
			// // newEntity.setId(entity.getId());
			// newEntity.setWord(entity.getWord());
			// newEntity.setCategoryFk(entity.getCategoryFk());
			//
			// newEntity = wordRepository.save(newEntity);
			//
			// return newEntity;
		} else {
			entity = wordRepository.save(entity);
			return entity;
		}
	}
     
    @Override
    public void deleteWordById(Long id) throws ValidationException 
    {
        Optional<Word> employee = wordRepository.findById(id);
         
        if(employee.isPresent()) 
        {
            wordRepository.deleteById(id);
        } else {
            throw new ValidationException("No word record exist for given id");
        }
    } 

    @Override
    public Word getWordByWordHQL(String word) throws ValidationException 
    {
        Optional<Word> wordBean = wordRepository.searchWordByWordDataHQL(word);
         
        if(wordBean.isPresent()) {
            return wordBean.get();
        } else {
            throw new ValidationException("No Word record exist for given word data");
        }
    }
   
}
