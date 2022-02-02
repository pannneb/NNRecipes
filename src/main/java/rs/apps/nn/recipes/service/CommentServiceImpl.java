package rs.apps.nn.recipes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import rs.apps.nn.recipes.domain.Comment;
import rs.apps.nn.recipes.domain.UnitOfMeasure;
import rs.apps.nn.recipes.repository.CommentRepository;
import rs.apps.nn.recipes.repository.UnitOfMeasureRepository;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;

	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Override
	public List<Comment> findAll() {
		List<Comment> comments = new ArrayList<>();
		commentRepository.findAll().forEach(comments::add);
		return comments.size() > 0 ? comments : null;
	}

	@Override
	public Comment findById(Long id) {
		return commentRepository.findById(id).orElse(null);
	}

	@Override
	public Comment save(Comment object) {
		return commentRepository.save(object);
	}

	@Override
	public void delete(Comment object) {
		commentRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		commentRepository.deleteById(id);
	}

	@Override
	public List<Comment> findAllByOrderByIdAsc() {
		return commentRepository.findAllByOrderByIdAsc();
	}

}
