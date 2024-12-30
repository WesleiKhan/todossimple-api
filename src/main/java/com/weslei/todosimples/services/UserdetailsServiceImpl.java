package com.weslei.todosimples.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.weslei.todosimples.models.User;
import com.weslei.todosimples.repositories.UserRepository;
import com.weslei.todosimples.security.UserSpringSecurity;

@Service
public class UserdetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(username);

        if (Objects.isNull(user)) {

            throw new UsernameNotFoundException("Usuario não encontrado " + username);
        }

        return new UserSpringSecurity(user.getId(), user.getUsername(), user.getPassword(), user.getProfiles());
    } 
    
}
