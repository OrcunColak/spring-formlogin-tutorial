package com.colak.springformlogintutorial.userdetailsservice;

import com.colak.springformlogintutorial.jpa.MyRole;
import com.colak.springformlogintutorial.jpa.MyUser;
import com.colak.springformlogintutorial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                myUser.getUsername(), myUser.getPasswd(), getAuthorities(myUser.getMyRoles()));
    }

    private Set<GrantedAuthority> getAuthorities(Set<MyRole> myRoles) {
        return myRoles.stream()
                .map(myRole -> new SimpleGrantedAuthority("ROLE_" + myRole.getName()))
                .collect(Collectors.toSet());
    }
}
