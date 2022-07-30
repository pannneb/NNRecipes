package rs.apps.nn.recipes.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
//
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String loginPage(@RequestParam(value = "error", required = false) String error,
//			@RequestParam(value = "logout", required = false) String logout, Model model) {
//		String errorMessge = null;
//		if (error != null) {
//			errorMessge = "Username or Password is incorrect !!";
//		}
//		if (logout != null) {
//			errorMessge = "You have been successfully logged out !!";
//		}
//		model.addAttribute("errorMessge", errorMessge);
//		return "login";
//	}
//
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
////		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////		if (auth != null) {
////			new SecurityContextLogoutHandler().logout(request, response, auth);
////		}
//		return "redirect:/login?logout=true";
//	}
}
