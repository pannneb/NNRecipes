package rs.apps.nn.recipes.service;

import java.util.List;

import rs.apps.nn.recipes.domain.Comment;

public interface CommentService extends CrudServiceRecipes<Comment, Long> {

	List<Comment> findAllByOrderByIdAsc();

}
