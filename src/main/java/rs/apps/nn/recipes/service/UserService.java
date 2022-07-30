package rs.apps.nn.recipes.service;

import java.util.Optional;

// import org.springframework.security.crypto.password.PasswordEncoder;

import rs.apps.nn.recipes.domain.User;

public interface UserService extends CrudServiceRecipes<User, Long> {

	Optional<User> findUserByEmail(String email);

	Optional<User> findUserByUserName(String userName);
	// PasswordEncoder passwordEncoder();
}
