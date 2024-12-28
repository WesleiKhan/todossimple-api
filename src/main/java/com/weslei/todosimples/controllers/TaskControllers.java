package com.weslei.todosimples.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.weslei.todosimples.models.Task;
import com.weslei.todosimples.services.TaskServices;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;







@RestController
@RequestMapping("/task")
@Validated
public class TaskControllers {

    @Autowired
    TaskServices taskServices;

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {

        Task obj = this.taskServices.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/tasks_user/{id}")
    public ResponseEntity<List<Task>> findAllByUserId(@PathVariable Long id) {

        List<Task> objs = this.taskServices.findAllByUserId(id);

        return ResponseEntity.ok().body(objs);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Task obj) {

        this.taskServices.create(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("Edit_task/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Task obj, @PathVariable Long id) {

        obj.setId(id);

        this.taskServices.update(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/Delete_task/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        this.taskServices.delete(id);

        return ResponseEntity.noContent().build();
    }
    
    
}
