package rs.apps.nn.recipes.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DecodeUsernamePass {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);

        rawPassword = "user";
        encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
   	
    }

}
