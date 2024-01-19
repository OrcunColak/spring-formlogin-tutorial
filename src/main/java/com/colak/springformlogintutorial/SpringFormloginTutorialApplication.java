package com.colak.springformlogintutorial;

import com.colak.springformlogintutorial.jpa.MyRole;
import com.colak.springformlogintutorial.jpa.MyUser;
import com.colak.springformlogintutorial.repository.RoleRepository;
import com.colak.springformlogintutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class SpringFormloginTutorialApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringFormloginTutorialApplication.class, args);
    }

    @Transactional
    @Override
    public void run(String... args) {

        createAdmin();
        createUser();
    }

    private void createUser() {
        // Create a role
        MyRole myRole = new MyRole();
        myRole.setName("USER");
        roleRepository.save(myRole);

        MyUser myUser = new MyUser();
        myUser.setUsername("user");
        myUser.setPasswd(passwordEncoder.encode("123456"));
        myUser.getMyRoles().add(myRole);
        userRepository.save(myUser);
    }

    private void createAdmin() {
        // Create a role
        MyRole myRole = new MyRole();
        myRole.setName("ADMIN");
        roleRepository.save(myRole);

        MyUser myUser = new MyUser();
        myUser.setUsername("admin");
        myUser.setPasswd(passwordEncoder.encode("123456"));
        myUser.getMyRoles().add(myRole);
        userRepository.save(myUser);
    }
}
