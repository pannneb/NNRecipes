package rs.apps.nn.recipes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import rs.apps.nn.recipes.domain.UnitOfMeasure;
import rs.apps.nn.recipes.repository.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private UnitOfMeasureRepository unitOfMeasureRepository;

	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public List<UnitOfMeasure> findAll() {
		List<UnitOfMeasure> measures = new ArrayList<>();
		unitOfMeasureRepository.findAll().forEach(measures::add);
		return measures.size() > 0 ? measures : null;
	}

	@Override
	public UnitOfMeasure findById(Long id) {
		return unitOfMeasureRepository.findById(id).orElse(null);
	}

	@Override
	public UnitOfMeasure save(UnitOfMeasure object) {
		return unitOfMeasureRepository.save(object);
	}

	@Override
	public void delete(UnitOfMeasure object) {
		unitOfMeasureRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		unitOfMeasureRepository.deleteById(id);
	}

	@Override
	public List<UnitOfMeasure> findAllByOrderByIdAsc() {
		return unitOfMeasureRepository.findAllByOrderByIdAsc();
	}

}
