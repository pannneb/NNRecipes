package rs.apps.nn.guessme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.apps.nn.guessme.model.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> , WordRepositoryCustom {

	Optional<Word> findById(Long wordId);

}


