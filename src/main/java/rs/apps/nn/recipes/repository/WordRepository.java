package rs.apps.nn.recipes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.apps.nn.recipes.model.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> , WordRepositoryCustom {

	Optional<Word> findById(Long wordId);

	Optional<Word> findByWord(String word);

}


