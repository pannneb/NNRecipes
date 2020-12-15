package rs.apps.nn.guessme.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.apps.nn.guessme.model.Word;
import rs.apps.nn.guessme.repository.WordRepository;

@Service
public class WordService {

    @Autowired
    WordRepository wordRepository;
     
    public List<Word> getAllWords()
    {
        List<Word> wordList = wordRepository.findAll();
         
        if(wordList.size() > 0) {
            return wordList;
        } else {
            return new ArrayList<Word>();
        }
    }
     
    public Word getWordById(Long id) throws ValidationException 
    {
        Optional<Word> word = wordRepository.findById(id);
         
        if(word.isPresent()) {
            return word.get();
        } else {
            throw new ValidationException("No word record exist for given id");
        }
    }

    public Word createOrUpdateWordByWord(Word entity) throws ValidationException 
    {
        // Optional<Word> word = wordRepository.findById(entity.getId());
        Optional<Word> word = wordRepository.findByWord(entity.getWord());

        if(word.isPresent()) 
        {
            Word newEntity = word.get();
            // newEntity.setId(entity.getId());
            newEntity.setWord(entity.getWord());
            newEntity.setCategoryFk(entity.getCategoryFk());
 
            newEntity = wordRepository.save(newEntity);
             
            return newEntity;
        } else {
            entity = wordRepository.save(entity);
             
            return entity;
        }
    } 
     
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
