package com.example.app.security.custom_beans;

import com.example.app.security.model.AppUser;
import com.example.app.security.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AppUserRepository repository;

    /** тут колбаса из приведения ролей в массив строк, суть - превратить изера приложения в представление юзера спринга*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!repository.existsByUsername(username)) {
            throw new UsernameNotFoundException("User not exist!");
        }
        AppUser appUser = repository.findByUsername(username);
        String[] roles = appUser.getRoles().stream().map(a->a.getName()).collect(Collectors.toSet()).toArray(new String[appUser.getRoles().size()]);
        return User.builder().username(appUser.getUsername()).password(appUser.getPassword()).roles(roles).build();
    }
}
