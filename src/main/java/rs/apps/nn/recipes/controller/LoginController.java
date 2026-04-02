package rs.apps.nn.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import rs.apps.nn.recipes.domain.EnumUserRole;
import rs.apps.nn.recipes.domain.User;
import rs.apps.nn.recipes.domain.UserRegistration;
import rs.apps.nn.recipes.service.UserService;

@Slf4j
@Controller
public class LoginController {
	private static final String VIEWS_REGISTRATION_FORM = "user/registration";
	private static final String VIEWS_LOGIN_FORM = "recipes/login";

    @Autowired
    private UserService userService;
//    
//    @Autowired
//    private UserValidator userValidator;

    @GetMapping({ "/user/registration" })
    public String registration(Model model) {
		model.addAttribute("userReg", new UserRegistration());
//        if (securityService.isAuthenticated()) {
//            return "redirect:/";
//        }
        return VIEWS_REGISTRATION_FORM;
    }

	@PostMapping({ "/user/registration" })
	public String submitUserRegistration(Model model, @Validated UserRegistration userReg, BindingResult result, final RedirectAttributes redirectAttributes) {
		log.debug("user AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA registration POST");
		// if (1==1) 
		//	return "redirect:/registration";
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error->{
				log.debug(error.toString());
			});
			model.addAttribute("userReg", userReg);
			return VIEWS_REGISTRATION_FORM;
		} else {
			if (userService.findUserByUserName(userReg.getUserName()).isEmpty()) {
		        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		        String encodedPassword = encoder.encode(userReg.getPassword());
				User u = User.builder().userName(userReg.getUserName())
						.password(encodedPassword)
						.email(null)
						.name("no data")
						.lastName("no data")
						.enabled(Boolean.TRUE)
						.role(EnumUserRole.ROLE_USER.getId()).build();
				userService.save(u);
				if (u.getId()>0) {
					User u2 = User.builder().userName(userReg.getUserName()).build();
					model.addAttribute("user", u2);
				}
				// model.addAttribute("userReg", userReg);
				redirectAttributes.addFlashAttribute("msg", "msg.user.register.addedSuccessfully");
				return "redirect:/login";
			} else {
				model.addAttribute("userReg", userReg);
				model.addAttribute("err", "msg.user.register.alreadyExists");
// 				redirectAttributes.addFlashAttribute("err", "msg.user.register.alreadyExists");
				// return "redirect:/user/registration";
				return VIEWS_REGISTRATION_FORM;
			}
//			user.setRole(EnumUserRole.ROLE_USER.getId());
//			user.setName("Dummy");
//			user.setLastName("Dummy");
//			Add message - success
//			User c = userService.save(user);
//			return "redirect:/recipe/login";
		}
		// return VIEWS_LOGIN_FORM;
	}

//	@PostMapping("/registration")
//    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
//        userValidator.validate(userForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//
//        userService.save(userForm);
//
//        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
//
//        return "redirect:/welcome";
//    }
	
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
        // custom logic before showing login page...
        return "login";
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
