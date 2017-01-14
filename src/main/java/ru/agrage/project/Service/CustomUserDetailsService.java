package ru.agrage.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agrage.project.Models.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 1/8/17.
 */

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserModel userModel = userService.getByUsername(username);
        System.out.println("User : "+userModel);
        if(userModel == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new User(userModel.getUsername(), userModel.getPassword(),
                true, true, true, true, getGrantedAuthorities(userService.getAll()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<UserModel> userModels){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(UserModel user : userModels) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
        }
        return authorities;
    }
}
