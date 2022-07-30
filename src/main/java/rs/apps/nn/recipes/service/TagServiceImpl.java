package rs.apps.nn.recipes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import rs.apps.nn.recipes.domain.Tag;
import rs.apps.nn.recipes.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService {

	private TagRepository tagRepository;

	public TagServiceImpl(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

//	@Override
//	public List<Tag> findAllByDescriptionLike(String name) {
//		return TagRepository.findAllByDescriptionLike(name);
//	}

	@Override
	public List<Tag> findAll() {
		List<Tag> tags = new ArrayList<>();
		tagRepository.findAll().forEach(tags::add);
		return tags.size() > 0 ? tags : null;
	}

	@Override
	public Tag findById(Long id) {
		return tagRepository.findById(id).orElse(null);
	}

	@Override
	public Tag save(Tag object) {
		return tagRepository.save(object);
	}

	@Override
	public void delete(Tag object) {
		tagRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		tagRepository.deleteById(id);
	}

	@Override
	public Optional<Tag> findByValue(String value) {
		return tagRepository.findByValue(value);
	}

//	@Override
//	public List<Tag> findAllByOrderByIdAsc() {
//		return TagRepository.findAllByOrderByIdAsc();
//	}

}
