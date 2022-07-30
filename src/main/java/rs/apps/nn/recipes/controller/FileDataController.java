package rs.apps.nn.recipes.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import rs.apps.nn.recipes.domain.FileData;
import rs.apps.nn.recipes.service.FileDataService;

@Slf4j
@Controller
@RequestMapping(path = "files")
public class FileDataController {

	private final FileDataService fileDataService;

	public FileDataController(FileDataService fileDataService) {
		super();
		this.fileDataService = fileDataService;
	}

	@GetMapping("/image/{id}")
	public void categoriesList(@PathVariable Long id, HttpServletResponse response) throws IOException {
		FileData fileData = fileDataService.findById(id);
		response.setContentType(fileData.getType()); // Or whatever format you wanna use
		InputStream is = new ByteArrayInputStream(fileData.getData());
		IOUtils.copy(is, response.getOutputStream());

	}

}
