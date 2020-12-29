package rs.apps.nn.guessme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {

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
