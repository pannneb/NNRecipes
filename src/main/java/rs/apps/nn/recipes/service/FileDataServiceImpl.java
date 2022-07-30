package rs.apps.nn.recipes.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import rs.apps.nn.recipes.domain.Category;
import rs.apps.nn.recipes.domain.FileData;
import rs.apps.nn.recipes.repository.FileDataRepository;

@Service
public class FileDataServiceImpl implements FileDataService {

	private FileDataRepository fileDataRepository;

	public FileDataServiceImpl(FileDataRepository fileDataRepository) {
		this.fileDataRepository = fileDataRepository;
	}

	@Override
	public List<FileData> findAll() {
		List<FileData> files = new ArrayList<>();
		fileDataRepository.findAll().forEach(files::add);
		return files.size() > 0 ? files : null;
	}

	@Override
	public FileData findById(Long id) {
		return fileDataRepository.findById(id).orElse(null);
	}

	@Override
	public FileData save(FileData object) {
		return fileDataRepository.save(object);
	}

	@Override
	public void delete(FileData object) {
		fileDataRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		fileDataRepository.deleteById(id);
	}

	public FileData store(MultipartFile file, Long imageId) throws IOException {
		String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
		FileData fileData = new FileData(fileName, file.getContentType(), file.getBytes());
		fileData.setId(imageId);
		return fileDataRepository.save(fileData);
	}

//	@Override
//	public List<FileData> findAllByRecipeFkOrderByIdDesc(Long id) {
//	    // return fileDataRepository.findAllByRecipeFkOrderByIdDesc(id);
//		return null;
//	}
//
//	@Override
//	public List<FileData> findAllByRecipeFkAndFileModeOrderByIdDesc(Long id, String fileMode) {
//	    //return fileDataRepository.findAllByRecipeFkAndFileModeOrderByIdDesc(id, fileMode);
//		return null;
//	}
	
//	@Override
//	public List<fileDataRepository> findAllByOrderByIdAsc() {
//		return fileDataRepository.findAllByOrderByIdAsc();
//	}

}
