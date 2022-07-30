package rs.apps.nn.recipes.service;


import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import rs.apps.nn.recipes.domain.FileData;

public interface FileDataService extends CrudServiceRecipes<FileData, Long> {

	// List<UnitOfMeasure> findAllByOrderByIdAsc();
	public FileData store(MultipartFile file, Long imageId) throws IOException;
	
	// public List<FileData> findAllByRecipeFkOrderByIdDesc(Long id);

	// public List<FileData> findAllByRecipeFkAndFileModeOrderByIdDesc(Long id, String fileMode);

}
