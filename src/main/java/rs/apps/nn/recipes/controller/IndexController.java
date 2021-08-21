package rs.apps.nn.recipes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(path = "/index")
public class IndexController {

	@ResponseBody
	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public String indexPage() {
//		Set<Recipe> listOfRecipes = recipeService.getRecipes();
//		model.addAttribute("recipes", listOfRecipes);
//
//		listOfRecipes.forEach(a->{
//			a.getCategories().forEach(c->{
//				System.out.println(c.toString());
//			});
//		});
//		
		return "index";
	}

	@ResponseBody
	@RequestMapping(value = { "/indexRecipes" }, method = RequestMethod.GET)
	public String indexPage2() {
		return "Recipes Index page - simple string";
	}
	
	@RequestMapping(value = { "/indexg" }, method = RequestMethod.GET)
	public String indexGuessMePage() {
//		Set<Recipe> listOfRecipes = recipeService.getRecipes();
//		model.addAttribute("recipes", listOfRecipes);
//
//		listOfRecipes.forEach(a->{
//			a.getCategories().forEach(c->{
//				System.out.println(c.toString());
//			});
//		});
//		
		return "guessme_index";
	}


	@RequestMapping(value = { "/index2" }, method = RequestMethod.GET)
	public String index2Page() {
//		Set<Recipe> listOfRecipes = recipeService.getRecipes();
//		model.addAttribute("recipes", listOfRecipes);
//
//		listOfRecipes.forEach(a->{
//			a.getCategories().forEach(c->{
//				System.out.println(c.toString());
//			});
//		});
//		
		return "index2";
	}

}
