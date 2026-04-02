package rs.apps.nn.recipes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {
	@GetMapping("/hello")
	public String hello() {
		return "Hello User Recipes, have a nice day.";
	}
}