package com.weslei.todosimples.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weslei.todosimples.models.User;
import com.weslei.todosimples.models.enums.ProfileEnum;
import com.weslei.todosimples.repositories.UserRepository;
import com.weslei.todosimples.services.exceptions.DataBindingViolationException;
import com.weslei.todosimples.services.exceptions.ObjectNotFoundException;

@Service
public class UserServices {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {

        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new ObjectNotFoundException(

        "Usuario não foi encontrado! Id: " + id + ", Tipo: " + User.class.getName() 
        ));
    }

    @Transactional
    public User create(User obj) {

        obj.setId(null);

        obj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));

        obj.setProfiles(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));

        obj = this.userRepository.save(obj);

        return obj;
    }
    
    @Transactional
    public User update(User obj) {

        User newObj = findById(obj.getId());

        newObj.setPassword(obj.getPassword());

        newObj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));

        return this.userRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {

        findById(id);

        try {
            this.userRepository.deleteById(id);

        } catch (Exception e) {

            throw new DataBindingViolationException("Não e possivel excluir usuario que tem relacionamentos com outras intidades");
        }
    }
}
