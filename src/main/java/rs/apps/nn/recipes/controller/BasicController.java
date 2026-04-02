package rs.apps.nn.recipes.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BasicController {

	public static String getLoggedUserName() {

    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String loggedUsername="";
    	if (principal instanceof UserDetails) {
    		loggedUsername = ((UserDetails)principal).getUsername();
    	} else {
    		loggedUsername = principal.toString();
    	}

    	return loggedUsername;
	}
	

	public static UserDetails getLoggedUser() {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (principal instanceof UserDetails) {
    		return (UserDetails)principal;
    	} else {
    		return null;
    	}
	}

}
