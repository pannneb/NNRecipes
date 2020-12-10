package rs.apps.nn.guessme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.apps.nn.guessme.bean.Word;

public interface WordRepositoryCustom {

	Optional<Word> searchDataByCustomVal(String word);

	Optional<Word> searchWordByWordDataHQL(String word);

}


