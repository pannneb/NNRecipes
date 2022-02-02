package rs.apps.nn.recipes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.apps.nn.recipes.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	Optional<Comment> findById(Long id);

	List<Comment> findAllByOrderByIdAsc();
	
	Optional<Comment> findByText(String text);

	List<Comment> findAllByTextLike(String text);

} 
