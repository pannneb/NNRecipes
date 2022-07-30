package rs.apps.nn.recipes.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rs.apps.nn.recipes.domain.Role;
import rs.apps.nn.recipes.domain.User;
import rs.apps.nn.recipes.repository.RoleRepository;
import rs.apps.nn.recipes.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Bean
//    @Override
//    public PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }
   
	public UserServiceImpl(UserRepository userRepository,
			RoleRepository roleRepository
			) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		// this.bCryptPasswordEncoder = (BCryptPasswordEncoder) passwordEncoder();
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(User user) {
        // user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        user.setEnabled(true);
        // Optional<Role> userRole = roleRepository.findByRole("ADMIN");
        // if (userRole.isPresent())
        //	user.setRoles(userRole.isPresent()?new HashSet<Role>(Arrays.asList(userRole.get())):new HashSet<Role>());
        return userRepository.save(user);
	}

	@Override
	public void delete(User object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}


}
