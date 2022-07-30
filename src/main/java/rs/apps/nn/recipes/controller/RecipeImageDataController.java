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
import rs.apps.nn.recipes.domain.RecipeImageData;
import rs.apps.nn.recipes.service.RecipeImageDataService;

@Slf4j
@Controller
@RequestMapping(path = "recipeImage")
public class RecipeImageDataController {

	private final RecipeImageDataService recipeImageDataService;

	public RecipeImageDataController(RecipeImageDataService recipeImageDataService) {
		super();
		this.recipeImageDataService = recipeImageDataService;
	}

	@GetMapping("/image/{id}")
	public void getImageDataById(@PathVariable Long id, HttpServletResponse response) throws IOException {
		RecipeImageData fileData = recipeImageDataService.findById(id);
		response.setContentType(fileData.getFileData().getType()); // Or whatever format you wanna use
		InputStream is = new ByteArrayInputStream(fileData.getFileData().getData());
		IOUtils.copy(is, response.getOutputStream());

	}

}
