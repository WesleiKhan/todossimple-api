package com.weslei.todosimples.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.weslei.todosimples.models.User;
import com.weslei.todosimples.models.User.CreateUser;
import com.weslei.todosimples.models.User.UpdateUser;
import com.weslei.todosimples.services.UserServices;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/user")
@Validated
public class UserControllers {

    @Autowired
    private UserServices userServices;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {

        User obj = this.userServices.findById(id); 

        return ResponseEntity.ok().body(obj);
    }
    
    @PostMapping
    @Validated(CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody User obj) {

        this.userServices.create(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/Edit_User/{id}")
    @Validated(UpdateUser.class)
    public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id) {

        obj.setId(id);

        this.userServices.update(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/Delete_User/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        this.userServices.delete(id);

        return ResponseEntity.noContent().build();
    }
      
}
